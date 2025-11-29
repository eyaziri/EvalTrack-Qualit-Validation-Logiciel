import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Reclamation {
  idReclamation?: number; // Id peut être optionnel
  type: 'ERREUR_NOTE_DS' | 'ERREUR_NOTE_EXAMEN' | 'NOTE_NON_AFFICHÉE' | 'DEMANDE_DOUBLE_CORRECTION_DS' | 'DEMANDE_DOUBLE_CORRECTION_EXAMEN' | 'AUTRE';
  dateCreation: string; 
  statut: 'EN_COURS' | 'TRAITEE' | 'ACCEPTEE' | 'REJETEE';
  reponseAdmin?: string;
  dateResolution?: string;
  nomProf: string;
  matiereConcerne: string;
  emailEtudiant: string;
  etudiant: {
    idEtudinat: number;
  };
  administrateur: {
    id: number;
  };
}
  

@Injectable({
  providedIn: 'root'
})
export class ReclamationService {
  private apiUrl = 'http://localhost:8080/EvalTrack/api/reclamations';

  getAllReclamations(): Observable<Reclamation[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          return response.json();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }
  addReclamation(reclamation: Reclamation): Observable<Reclamation> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}`, {
        method: 'POST',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(reclamation)
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Erreur HTTP ' + response.status);
          }
          return response.json();
        })
        .then(data => {
          observer.next(data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
        });
    });
  }
  

  getReclamationById(idReclamation: number): Observable<any[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/${idReclamation}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          return response.json();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }

  getReclamationsByEtudiant(idEtudinat: number): Observable<any[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/etudiant/${idEtudinat}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          return response.json();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }

  

  getReclamationsByStatut(statut: string): Observable<Reclamation[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/statut/${statut}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          return response.json();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }

  getReclamationsByType(type: string): Observable<Reclamation[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/type/${type}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          return response.json();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }

  

  deleteReclamation(id: number): Observable<void> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/${id}`, {
        method: 'DELETE',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          observer.next();
          observer.complete();
        })
        .catch(error => observer.error(error));
    });
  }

  getReclamationsAfterDate(date: string): Observable<Reclamation[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/dateAfter/${encodeURIComponent(date)}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          return response.json();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }
  

  getResolvedReclamations(): Observable<Reclamation[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/resolved`, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) throw new Error('HTTP status ' + response.status);
          return response.json();
        })
        .then(data => observer.next(data))
        .catch(error => observer.error(error));
    });
  }

  changerStatut(idReclamation: number, nouveauStatut: string): Observable<string> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/${idReclamation}/changer-statut/${nouveauStatut}`, {
        method: 'PUT',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if (!response.ok) throw new Error('HTTP status ' + response.status);
        return response.text(); // Le backend retourne un texte, pas du JSON
      })
      .then(data => {
        observer.next(data); // data = "Statut mis à jour"
        observer.complete();
      })
      .catch(error => {
        observer.error(error);
      });
    });
  }

  getReclamationsByAdmin(idAdmin: number): Observable<Reclamation[]> {
    return new Observable((observer) => {
      fetch(`${this.apiUrl}/admin/${idAdmin}`, {  // Ajoute l'endpoint pour récupérer par admin
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa('admin:123'),
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if (!response.ok) throw new Error('HTTP status ' + response.status);
        return response.json();
      })
      .then(data => observer.next(data))
      .catch(error => observer.error(error));
    });
  }
  
  
  
}
