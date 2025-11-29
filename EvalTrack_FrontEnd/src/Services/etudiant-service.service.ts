import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EtudiantServiceService {
  private apiUrl = 'http://localhost:8080/EvalTrack/etudiant/CreateEtudiant';
  constructor() { }

  addStudent(student: any): Observable<any> {
    return new Observable((observer) => {
      fetch(this.apiUrl, {
        method: 'POST',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(student) 
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



  getEtudiantsBySectionAndNiveau(sectionId: number, niveau: number): Observable<any[]> {
    const apiUrl = `http://localhost:8080/EvalTrack/etudiant/bySectionAndNiveau/${sectionId}/${niveau}`;
  
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
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

  
  LoginStudent(student: any): Observable<any> {
    return new Observable((observer) => {
      const headers = {
        'Authorization': 'Basic ' + btoa('admin:123'), // ou utilisez student.email/motDePasse si auth dynamique
        'Content-Type': 'application/json',
      };
  
      // Première tentative : login étudiant
      fetch(`http://localhost:8080/EvalTrack/etudiant/LoginEtudiant`, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(student)
      })
      .then(response => {
        if (response.status === 401) {
          // Si non autorisé → tenter l'API admin
          console.warn("Échec étudiant. Tentative via LoginAdmin...");
          return fetch(`http://localhost:8080/EvalTrack/auth/LoginAdmin`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(student)
          });
        }
        return response;
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('HTTP status ' + response.status);
        }
        return response.json();
      })
      .then(data => {
        observer.next(data);
        observer.complete();
      })
      .catch(error => {
        console.error('Erreur lors de la requête : ', error);
        observer.error(error);
      });
    });
  }
  
  sendVerificationCode( email:string): Observable<String> {
    const apiUrl = `http://localhost:8080/EvalTrack/etudiant/send-verification-code/${email}`;
  
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('HTTP status ' + response.status);
          }
          return response.text();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }
  VerifyCode(email: string, code: string): Observable<string> {
    const apiUrl = `http://localhost:8080/EvalTrack/etudiant/verify-code/${email}/${code}`;
  
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('HTTP status ' + response.status);
        }
        return response.text(); // tu reçois une string
      })
      .then(data => observer.next(data)) // ici, data est bien une string
      .catch(error => observer.error(error));
    });
  }
  
  updatepassword(email: string, newPass: string, oldPass: string, idRole: string): Observable<string> {
    const apiUrl = `http://localhost:8080/EvalTrack/etudiant/update-password?email=${encodeURIComponent(email)}&oldPassword=${encodeURIComponent(oldPass)}&newPassword=${encodeURIComponent(newPass)}&idRole=${encodeURIComponent(idRole)}`;
  
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'PUT',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('HTTP status ' + response.status);
        }
        return response.text();
      })
      .then(data => observer.next(data))
      .catch(error => observer.error(error));
    });
  }

  getStudentById(idStudent :number)
  {
    const apiUrl = `http://localhost:8080/EvalTrack/etudiant/${idStudent}`;
  
    return new Observable<any>((observer) => {
      fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
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

  getUserByIdAndRole(idStudent :number,idRole:number)
  {
    const apiUrl = `http://localhost:8080/EvalTrack/etudiant/${idStudent}/${idRole}`;
  
    return new Observable<any>((observer) => {
      fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
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
