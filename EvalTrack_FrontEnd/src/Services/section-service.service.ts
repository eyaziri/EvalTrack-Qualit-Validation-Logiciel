import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SectionServiceService {
  private apiUrl = 'http://localhost:8080/EvalTrack/sections';
  constructor() { }

  getSections(): Observable<any[]> {
    return new Observable((observer) => {
      fetch(this.apiUrl, {
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
