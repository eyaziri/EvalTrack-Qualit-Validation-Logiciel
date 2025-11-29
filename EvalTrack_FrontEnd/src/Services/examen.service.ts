import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ExamenService {
  private apiUrl = 'http://localhost:8080/EvalTrack/examen';
  constructor() { }
  addNote(exam: any): Observable<any> {
    return new Observable((observer) => {
      fetch(this.apiUrl, {
        method: 'POST',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(exam) 
      })
      .then(response => {
        if (response.status === 409) {
            throw new Error('Un examen existe déjà pour cet étudiant, matière, type et session.');
        }
        if (!response.ok) {
            throw new Error('HTTP status ' + response.status);
        }
        return response.json();
    })    
      .then(data => observer.next(data))
      .catch(error => observer.error(error));
    });
  }


  getNoteByStudent(idStudent:number): Observable<any> {
    return new Observable((observer) => {
      fetch(`http://localhost:8080/EvalTrack/examen/getByStudent/${idStudent}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('HTTP status ' + response.status);
        }
        return response.json();
      })
      .then(data => observer.next(data))
      .catch(error => observer.error(error));
    });
  }
  updateNoteByStudent(cinStudent:number,idMatiere :number,typeExam:String,note:number,session:String): Observable<any> {
    return new Observable((observer) => {
      fetch(`http://localhost:8080/EvalTrack/examen/update-note/${cinStudent}/${idMatiere}/${typeExam}/${note}/${session}`, {
        method: 'PUT',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('HTTP status ' + response.status);
        }
        return response.json();
      })
      .then(data => observer.next(data))
      .catch(error => observer.error(error));
    });
  }
}
