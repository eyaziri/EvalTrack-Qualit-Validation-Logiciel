// src/app/services/module.service.ts
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Module } from '../app/types';

@Injectable({
  providedIn: 'root'
})
export class ModuleService {
  private apiUrl = 'http://localhost:8080/EvalTrack/modules';

  constructor() { }

  getModule(idsection: number, semestre: number): Observable<any[]> {
    const apiUrl = `http://localhost:8080/EvalTrack/modules/by-section/${idsection}/${semestre}`;
    
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
      .then(async response => {
        if (!response.ok) {
          // Try to get error details from response
          const error = await response.json().catch(() => ({}));
          throw new Error(
            `HTTP ${response.status}: ${response.statusText}\n` +
            JSON.stringify(error, null, 2)
          );
        }
        return response.json();
      })
      .then(data => {
        observer.next(data);
        observer.complete();
      })
      .catch(error => {
        console.error('API Error Details:', error);
        observer.error(error);
      });
    });
  }

  // Dans module.service.ts
  createModuleWithMatieres(moduleWithMatieres: any): Observable<any> {
    const apiUrl = 'http://localhost:8080/EvalTrack/modules/with-matieres';
    
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(moduleWithMatieres)
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

  getModulesBySectionAndSemester(idsection: number, semestre: number): Observable<Module[]> {
    const apiUrl = `http://localhost:8080/EvalTrack/modules/by-section/${idsection}/${semestre}`;
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
        observer.next(data); // Envoie les données dans l'observable
        observer.complete();  // Complète l'observable une fois les données reçues
      })
      .catch(error => observer.error(error)); // Capture les erreurs et les envoie dans l'observable
    });
  }

  getModuleMatiere(idsection: number,semestre:number): Observable<any[]> {
    const apiUrl = `http://localhost:8080/EvalTrack/modules/section/${idsection}/semestre/${semestre}`;
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
  

  getMatieres(idsection: number,semestre:number): Observable<any[]> {
    const apiUrl = `http://localhost:8080/EvalTrack/modules/matieres/${idsection}/${semestre}`;
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

  createModule(module: Omit<Module, 'idModule'>): Observable<Module> {
    const apiUrl = 'http://localhost:8080/EvalTrack/modules';  // L'URL de l'API pour créer un module
  
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(module)  // Envoie l'objet module sous forme de chaîne JSON
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('HTTP status ' + response.status);
        }
        return response.json();
      })
      .then(data => {
        observer.next(data);  // Envoie les données reçues dans l'observable
        observer.complete();  // Complète l'observable
      })
      .catch(error => {
        observer.error(error);  // Capture l'erreur et la transmet à l'observable
      });
    });
  }
  

  updateModule(id: number, module: Partial<Module>): Observable<Module> {
    console.log("Données envoyées:", { id, module }); 
    const apiUrl = `http://localhost:8080/EvalTrack/modules/${id}`;  // L'URL de l'API pour mettre à jour un module
  
    return new Observable((observer) => {
      fetch(apiUrl, {
        method: 'PUT',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(module)  // Envoie les modifications du module sous forme de chaîne JSON
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('HTTP status ' + response.status);
        }
        return response.json();
      })
      .then(data => {
        observer.next(data);  // Envoie les données mises à jour à l'observable
        observer.complete();  // Complète l'observable
      })
      .catch(error => {
        observer.error(error);  // Capture l'erreur et la transmet à l'observable
      });
    });
  }
  
  deleteModule(id: number): Observable<void> {
    const apiUrl = `http://localhost:8080/EvalTrack/modules/${id}`;  // L'URL de l'API pour supprimer un module
  
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
          throw new Error('HTTP status ' + response.status);  // Lancer une erreur si la suppression échoue
        }
        observer.next();  // Aucun contenu à renvoyer, on signale que l'opération est terminée
        observer.complete();  // Complète l'observable
      })
      .catch(error => {
        observer.error(error);  // Capture l'erreur et la transmet à l'observable
      });
    });
  }
  
  private getAuthHeaders(): { [key: string]: string } {
    return {
      'Authorization': 'Basic ' + btoa('admin:123'),
      'Content-Type': 'application/json'
    };
  }
  
}