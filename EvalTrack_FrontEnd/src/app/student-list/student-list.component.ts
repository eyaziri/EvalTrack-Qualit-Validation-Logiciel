import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as XLSX from 'xlsx';
import { SectionServiceService } from '../../Services/section-service.service';
import { EtudiantServiceService } from '../../Services/etudiant-service.service';
@Component({
  selector: 'app-student-list',
  standalone: true,
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss'],

  imports: [CommonModule,FormsModule]
})

export class StudentListComponent implements OnInit {
etudiants: any[] = [];
sections: any[] = [];
action: string = 'consulter';
selectedSemestre: string = '';
selectedSection: string = '';
semestres = ['Niveau 1', 'Niveau 2','Niveau 3'];
newStudent = {
  nom: "Ameni",
  email: "Ameni@gmail.com",
  motDePasse: "Ameni123",
  cin: 14415634,
  niveau: 14,
  section: 0 ,
  role:2 
};

successMessage: string = '';
errorMessage: string = '';
constructor(private sectionS: SectionServiceService , private studentService :EtudiantServiceService) {


  }

  ngOnInit() {
  
    this.sectionS.getSections().subscribe({
      next: (data) => {
        console.log('Sections received:', data);
       
        this.sections = data.map(section => ({
          idSection: section.idSection,           
          nomSection: section.nomSection  
        }));
      },
      error: (err) => {
        console.error('Error fetching sections:', err);
      }
    });
  }
  
  

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) return;

    const file: File = input.files[0];
    const reader = new FileReader();

    reader.onload = (e) => {
      const arrayBuffer = reader.result as ArrayBuffer;
      const workbook = XLSX.read(arrayBuffer, { type: 'array' });
      const sheetName = workbook.SheetNames[0];
      const sheet = workbook.Sheets[sheetName];
      const data = XLSX.utils.sheet_to_json(sheet, { header: 1 });

      data.shift(); // Enlever la ligne d'en-tête
      this.etudiants = data;
    };

    
    reader.readAsArrayBuffer(file);
  }

  addStudentsToDB() {
    if(this.selectedSection=='' && this.selectedSemestre=='')
    {
        console.log ("error");
    }
    else
    {
    this.etudiants.forEach(student => {
      this.newStudent.cin = student[0];
      this.newStudent.nom = student[1] + " " + student[2];
      this.newStudent.email = student[3];
      this.newStudent.motDePasse = student[4];
      this.newStudent.niveau= parseInt(this.selectedSemestre.charAt(this.selectedSemestre.length - 1));
      this.newStudent.section= parseInt(this.selectedSection);
      this.newStudent.role=2;
      console.log("student"+this.newStudent);
      this.studentService.addStudent(this.newStudent).subscribe({
        next: (data) => {
          console.log('Student added:', data);
          this.successMessage = 'Étudiant ajouté avec succès !'; 
          setTimeout(() => {
            this.successMessage = '';
          }, 6000);
         
        },
        error: (err) => {
          console.error('Error adding student:', err);
          this.errorMessage = 'Erreur lors de l\'ajout de l\'étudiant.'; 
          setTimeout(() => {
            this.errorMessage = '';
          }, 6000);
        }
      });
    });
  }
  }
  
  listStudent()
   {
    this.studentService.getEtudiantsBySectionAndNiveau(parseInt(this.selectedSection),parseInt(this.selectedSemestre.charAt(this.selectedSemestre.length - 1)))
  .subscribe({
    next: (etudiants) => {
      this.etudiants = etudiants;
      console.log("Etudiants récupérés : ", this.etudiants);
     
    },
    error: (err) => {
      console.error("Erreur lors de la récupération des étudiants : ", err);
     
    }
  });

   }

  

 }
