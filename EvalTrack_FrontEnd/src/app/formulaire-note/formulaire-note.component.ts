import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms'; // <-- AJOUTER ÇA
import { SectionServiceService } from '../../Services/section-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModuleService } from '../../Services/module.service';
import { MatiereService } from '../../Services/matiere.service';
import { ExamenService } from '../../Services/examen.service';

@Component({
  selector: 'app-formulaire-note',
  standalone: true,
  imports: [ReactiveFormsModule ,CommonModule, FormsModule],
  templateUrl: './formulaire-note.component.html',
  styleUrl: './formulaire-note.component.scss'
})
export class FormulaireNoteComponent {
  noteForm!: FormGroup ;
  
  selectedMatiere = '';
  selectedTypeDevoir = '';
  selectedSession = '';
  selectedModule = '';
  selectedSemestre = '';
  selectedSection = '';
  successMessage: string = '';
  errorMessage: string = '';
  
  sessions = ['Principal', 'Rattrapage'];
  semestres = [
    'Semestre 1',
    'Semestre 2',
    'Semestre 3',
    'Semestre 4',
    'Semestre 5',
  ];
  sections: any[] = [];
  modules: any[] = [];
  matieres: any[] = [];
  idSection = 0;
  idMatiere= 0;
  typeExam='';
  session=''

  constructor(private fb: FormBuilder,private sectionS :SectionServiceService,private module :ModuleService, private matiereService:MatiereService ,private examenService:ExamenService)
  {

  }
  ngOnInit(): void {
  
    this.sectionS.getSections().subscribe({
      next: (data) => {
        console.log('Sections received:', data);

        this.sections = data.map((section) => ({
          idSection: section.idSection,
          nomSection: section.nomSection,
        }));
      },
      error: (err) => {
        console.error('Error fetching sections:', err);
      },
    });
  
    this.noteForm = this.fb.group({
      session: ['', Validators.required],
      section: ['', Validators.required],
      semestre: ['', Validators.required],
      module: ['', Validators.required],
      matiere: ['', Validators.required],
      type_exam: ['', Validators.required],
      note: ['', [Validators.required, Validators.min(0), Validators.max(20)]],
      cin: ['', [Validators.required]]
    });
  }
  onSectionChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const selectedSection = target.value;
      this.idSection = parseInt(selectedSection);
    }
  }
  onMatiereChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const selectedMatiere = target.value;
      this.idMatiere = parseInt(selectedMatiere );
    }
  }

  onTypeExamChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const selectedTypeDevoir = target.value;
      this.typeExam = selectedTypeDevoir;
    }
  }
  onSessionChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const selectedSession = target.value;
      this.session = selectedSession;
    }
  }
  onSemestreChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const lastChar = target.value.slice(-1);
      const semestre = parseInt(lastChar);

      this.module.getModule(this.idSection, semestre).subscribe({
        next: (data) => {
          console.log('Modules received:', data); 

         
          if (Array.isArray(data)) {
            this.modules = data.map((module) => ({
              idModule: module.idModule,
              nomModule: module.nomModule,
            }));
          } else {
            console.error('Data is not an array', data); // Si ce n'est pas un tableau, affiche l'erreur
          }
        },
        error: (err) => {
          console.error('Error fetching modules:', err);
        },
      });
    }
  }

  onModuleChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const selectedModule = target.value;
      const id = parseInt(selectedModule);

      this.matiereService.getMatieres(id).subscribe({
        next: (data) => {
          console.log('Matiere received:', data); // Pour vérifier si la réponse est bien un tableau

          // Vérifie que data est un tableau avant d'utiliser .map()
          if (Array.isArray(data)) {
            this.matieres = data.map((matiere) => ({
              idMatiere: matiere.matiereId,
              nomMatiere: matiere.nom,
            }));
          } else {
            console.error('Data is not an array', data); // Si ce n'est pas un tableau, affiche l'erreur
          }
        },
        error: (err) => {
          console.error('Error fetching modules:', err);
        },
      });
    }
  }
  onSubmit(): void {
    if (this.noteForm.valid) {
      const formValues = this.noteForm.value;
      console.log(this.noteForm.value);
      this.examenService.updateNoteByStudent(formValues.cin, formValues.matiere, formValues.type_exam,formValues.note, formValues.session)
      .subscribe({
        next: (response) => {
          console.log('Note updated successfully:', response);
          this.successMessage = 'La note a été mise à jour avec succès !';
          this.errorMessage = '';
        },
        error: (error) => {
          console.error('Error updating note:', error);
          this.errorMessage = 'Une erreur est survenue lors de la mise à jour.';
          this.successMessage = '';
        }
      });

      
    }
  }

}
