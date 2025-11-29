import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserRole: string = ''; // Role de l'utilisateur (peut être 'admin' ou 'etudiant')
  
  private user = {
    role: 'admin' // Exemple de rôle, vous devriez récupérer cette valeur depuis un JWT ou un autre mécanisme d'authentification.
  };
  constructor() { }

  // Simuler l'authentification et l'attribution du rôle
  login(role: string): void {
    this.currentUserRole = role;
    localStorage.setItem('role', role); // Simuler un stockage du rôle
  }
  getUserRole(): string {
    return localStorage.getItem('idRole') ?? ''; // retourne '' si null
  }
  

  logout(): void {
    this.currentUserRole = '';
    localStorage.removeItem('role'); // Supprimer le rôle lors de la déconnexion
  }

  // Récupérer le rôle actuel
  getRole(): string {
    return this.currentUserRole || localStorage.getItem('role') || '';
  }

  // Vérifier si l'utilisateur est connecté
  isLoggedIn(): boolean {
    return !!this.getRole(); // Si un rôle est présent, l'utilisateur est connecté
  }
}
