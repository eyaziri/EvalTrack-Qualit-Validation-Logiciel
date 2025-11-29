import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { SectionServiceService } from '../../Services/section-service.service';
import { ModuleService } from '../../Services/module.service';
import { ExamenService } from '../../Services/examen.service';
import { EtudiantServiceService } from '../../Services/etudiant-service.service';

@Component({
  selector: 'app-notes-table',
  imports: [CommonModule, NgFor, FormsModule],
  templateUrl: './notes-table.component.html',
  styleUrl: './notes-table.component.scss',
})
export class NotesTableComponent {
  constructor(
    private moduleService: ModuleService,
    private examenService: ExamenService,
    private studentService: EtudiantServiceService
  ) {}
  ngOnInit() {
    const idEtudiantString = localStorage.getItem('idUser');

    if (idEtudiantString !== null) {
      const idUser = parseInt(idEtudiantString, 10);
      this.studentService.getStudentById(idUser).subscribe({
        next: (data: any) => {
          this.student = {
            idEtudiant: data.idEtudinat,
            nomEtudiant: data.nom,
            cinEtudiant: data.cin,
            niveauEtudiant: data.niveau,
            idSection: data.section.idSection,
          };
          if (this.student.niveauEtudiant == 1) {
            this.filteredSemestres = ['Semestre 1', 'Semestre 2'];
          } else if (this.student.niveauEtudiant == 2) {
            this.filteredSemestres = ['Semestre 1', 'Semestre 2','Semestre 3', 'Semestre 4'];
          } else if (this.student.niveauEtudiant == 3) {
            this.filteredSemestres = ['Semestre 1', 'Semestre 2','Semestre 3', 'Semestre 4','Semestre 5'];
          } else {
            this.filteredSemestres = []; // Aucun semestre si niveau inconnu
          }
        },
        error: (err) => {
          console.error('Error fetching student:', err);
        },
      });
    }
  }

  headers = [
    'UE',
    'COEF UE',
    'MATIERE',
    'COEF MATIERE',
    'PONDERATION',
    'CC',
    'EXAM',
    'MOYENNE',
    'Moyenne unité',
    'ETAT',
    'TELECHARGEMENT',
  ];

  selectedNiveau = '';
  selectedSemestre = '';

  modules: any[] = [];
  matieres: any[] = [];
  exams: any[] = [];
  student: any = {};
  niveaux = ['Niveau 1', 'Niveau 2', 'Niveau 3'];
  filteredSemestres: string[] = [];

  semestres = [
    'Semestre 1',
    'Semestre 2',
    'Semestre 3',
    'Semestre 4',
    'Semestre 5',
  ];
  isProcessing = false;

  handleDownload(matiere: any) {
    if (matiere.lienCopie && matiere.lienCopie !== '-') {
      const link = document.createElement('a');
      link.href = matiere.lienCopie;
      link.download = ''; // facultatif : si vide → nom pris depuis l'URL
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    } else {
      console.warn('Aucun lien de copie disponible');
    }
  }

  onSemestreChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const lastChar = this.selectedSemestre.slice(-1);
      const semestre = parseInt(lastChar);

      this.moduleService
        .getMatieres(this.student.idSection, semestre)
        .subscribe({
          next: (data) => {
            // Vérifie que data est un tableau avant d'utiliser .map()
            if (Array.isArray(data)) {
              console.log(data);
              this.modules = data.map((item) => ({
                idModule: item.module.idModule,
                nomModule: item.module.nomModule,
                coefModule: item.module.coefModule,
                matieres: item.matieres.map((matiere: any) => ({
                  idMatiere: matiere.matiereId,
                  nomMatiere: matiere.nom,
                  coefMatiere: matiere.coefficient,
                  ponderation: matiere.ponderation,
                })),
              }));

              const idEtudiantString = localStorage.getItem('idUser');

              if (idEtudiantString !== null) {
                const idUser = parseInt(idEtudiantString, 10);

                this.examenService.getNoteByStudent(idUser).subscribe({
                  next: (data) => {
                    this.exams = data.map((exam: any) => ({
                      idExam: exam.idExam,
                      idMatiere: exam.matiere.matiereId,
                      typeExam: exam.typeExam,
                      lienCopie:
                        exam.typeExam === 'Examen' ? exam.lienCopie : '',
                      noteExamen: exam.typeExam === 'Examen' ? exam.notes : '',
                      noteDS: exam.typeExam === 'DS' ? exam.notes : '',
                    }));
                    this.modules.forEach((module) => {
                      module.matieres.forEach((matiere: any) => {
                        const examsOfMatiere = this.exams.filter(
                          (e) => e.idMatiere === matiere.idMatiere
                        );

                        const exam = examsOfMatiere.find(
                          (e) => e.typeExam === 'Examen'
                        );
                        const ds = examsOfMatiere.find(
                          (e) => e.typeExam === 'DS'
                        );

                        matiere.cc = ds ? ds.noteDS : '-';
                        matiere.exam = exam ? exam.noteExamen : '-';
                        matiere.lienCopie = exam
                          ? exam.lienCopie
                          : ds
                          ? ds.lienCopie
                          : '-';

                        const ponderationMatch = matiere.ponderation.match(
                          /(\d+(\.\d+)?)(?=\*DS)/
                        );
                        const ponderationExamMatch = matiere.ponderation.match(
                          /(\d+(\.\d+)?)(?=\*Examen)/
                        );

                        if (matiere.cc >= 0 && matiere.exam >= 0) {
                          matiere.moyenne = (
                            parseFloat(ponderationMatch[0]) * matiere.cc +
                            parseFloat(ponderationExamMatch[1]) * matiere.exam
                          ).toFixed(2);
                        } else {
                          matiere.moyenne = '-';
                        }
                      });

                      // ✅ Ici, après avoir rempli toutes les moyennes des matières :
                      const allMatieresHaveMoyenne = module.matieres.every(
                        (matiere: any) => matiere.moyenne !== '-'
                      );

                      if (allMatieresHaveMoyenne) {
                        let totalCoefficients = 0;
                        let totalNotes = 0;

                        module.matieres.forEach((matiere: any) => {
                          totalCoefficients += matiere.coefMatiere;
                          totalNotes += matiere.moyenne * matiere.coefMatiere;
                        });

                        module.moyenneModule =
                          totalCoefficients > 0
                            ? (totalNotes / totalCoefficients).toFixed(2)
                            : '-';
                      } else {
                        module.moyenneModule = '-';
                      }
                    });
                  },
                  error: (err) => {
                    console.error('Error fetching sections:', err);
                  },
                });
              } else {
                console.error(
                  'idUser not found in localStorage. Cannot fetch notes.'
                );
              }
            } else {
              console.error('Data is not an array', data);
            }
          },
          error: (err) => {
            console.error('Error fetching modules:', err);
          },
        });
    }
  }
}
