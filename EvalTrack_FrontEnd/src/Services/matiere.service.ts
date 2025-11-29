// src/app/services/matiere.service.ts
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Matiere } from '../app/types';

@Injectable({
  providedIn: 'root'
})
export class MatiereService {
  private apiUrl = 'http://localhost:8080/EvalTrack/matieres';

  constructor() { }
  getMatieres(idModule: number): Observable<any[]> {
    const apiUrl =`http://localhost:8080/EvalTrack/matieres/module/${idModule}`;
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
      .then(data => {
        observer.next(data);
        observer.complete();  // <-- à ajouter
      })
      .catch(error => observer.error(error));
    });
  }


  getMatieresByModule(idModule: number): Observable<Matiere[]> {
    const apiUrl = `${this.apiUrl}/by-module/${idModule}`;
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
        .then(data => {
          observer.next(data);
          observer.complete();
        })
        .catch(error => observer.error(error));
    });
  }
  
  addMatiere(matiere: Omit<Matiere, 'idMatiere'>): Observable<Matiere> {
    const apiUrl = this.apiUrl;
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'POST',
        headers:  {
          'Authorization': 'Basic ' + btoa('admin:123'), 
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(matiere)
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
        .catch(error => observer.error(error));
    });
  }
  
  updateMatiere(id: number, matiere: Partial<Matiere>): Observable<Matiere> {
    const apiUrl = `${this.apiUrl}/${id}`;
    
    // Créez un objet simplifié sans les références circulaires
    const matiereToSend = {
      nom: matiere.nom,
      coefficient: matiere.coefficient,
      description: matiere.description,
      ponderation: matiere.ponderation,
      moyenne: matiere.moyenne,
      };
  
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'PUT',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json',
          'Accept': 'application/json' // Ajoutez ce header
        },
        body: JSON.stringify(matiereToSend)
      })
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP status ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        observer.next(data);
        observer.complete();
      })
      .catch(error => observer.error(error));
    });
  }

  deleteMatiere(id: number): Observable<void> {
    const apiUrl = `${this.apiUrl}/${id}`;
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'DELETE',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'), 
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('HTTP status ' + response.status);
          }
          observer.next();
          observer.complete();
        })
        .catch(error => observer.error(error));
    });
  }
  
  

  addMatiereToModule(matiere: any,idModule:number): Observable<any> {
    return new Observable((observer) => {
      fetch(`http://localhost:8080/EvalTrack/matieres/for-module/${idModule}`, {
        method: 'POST',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(matiere) 
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