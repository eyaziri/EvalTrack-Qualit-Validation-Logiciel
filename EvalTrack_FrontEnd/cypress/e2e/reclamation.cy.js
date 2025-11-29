describe('Formulaire de réclamation', () => {
  beforeEach(() => {
    // Simule un étudiant connecté dans le localStorage
    window.localStorage.setItem('idRole', '2');
    window.localStorage.setItem('idUser', '138');

    // Ouvre la page du composant de réclamation
    cy.visit('http://localhost:4200/etudiant-page-reclamation'); // 🔁 adapte le chemin selon ta route Angular
  });

  it('Affiche le titre du formulaire', () => {
    cy.contains('Soumettre une reclamation').should('be.visible');
  });

  it('Permet de remplir et soumettre le formulaire', () => {
    // Sélection du type de réclamation
    cy.get('select[name="type"]').select('ERREUR_NOTE_DS');
    
    // Remplir la matière concernée
    cy.get('input[name="matiereConcerne"]').type('Programmation Orientée Objet');
    
    // Remplir le nom de l’enseignant
    cy.get('input[name="nomProf"]').type('Sana Nouira');
    
    // Intercepter la requête POST d’ajout d’une réclamation (mock du backend)
    cy.intercept('POST', 'http://localhost:8080/EvalTrack/api/reclamations', {
      statusCode: 201,
      body: {
        id: 25,
        type: 'ERREUR_NOTE_DS',
        matiereConcerne: 'Programmation Orientée Objet',
        nomProf: 'Sana Nouira',
        statut: 'EN_COURS',
        dateCreation: new Date().toISOString(),
        etudiant: { idEtudinat: 138 },
        administrateur: {id: 4}
      },
    }).as('ajoutReclamation');
    
    // Soumettre le formulaire
    cy.get('button[type="submit"]').click();
    
    // Vérifie que la requête a bien été envoyée et reçue
    cy.wait('@ajoutReclamation').its('response.statusCode').should('eq', 201);
  });

  it('Affiche les réclamations précédentes de l’étudiant', () => {
    // Simule la réponse du service backend
    cy.intercept('GET', 'http://localhost:8080/EvalTrack/api/reclamations/etudiant/138', {
      statusCode: 200,
      body: [
        {
          id: 5,
          type: 'ERREUR_NOTE_EXAMEN',
          matiereConcerne: 'Programmation Orientée Objet',
          nomProf: 'Sana Nouira',
          statut: 'ACCEPTEE',
          dateCreation: '2025-10-20T09:00:00Z',
        },
      ],
    }).as('getReclamations');
    
    cy.visit('http://localhost:4200/etudiant-page-reclamation');
    cy.wait('@getReclamations');
    
    cy.contains('Réclamations précédentes').should('be.visible');
    cy.contains('Programmation Orientée Objet').should('be.visible');
    cy.contains('Sana Nouira').should('be.visible');
  });

  it('Affiche correctement le workflow pour une réclamation traitée', () => {
    cy.intercept('GET', 'http://localhost:8080/EvalTrack/api/reclamations/etudiant/138', {
      statusCode: 200,
      body: [
        {
          id: 10,
          type: 'NOTE_NON_AFFICHÉE',
          matiereConcerne: 'Analyse de données',
          nomProf: 'Sana Nouira',
          statut: 'TRAITEE',
          dateCreation: '2025-10-18T12:00:00Z',
        },
      ],
    }).as('getWorkflow');

    cy.visit('http://localhost:4200/etudiant-page-reclamation');
    cy.wait('@getWorkflow');

   cy.get('.workflow-section').scrollIntoView().should('be.visible');
    cy.get('.step-number.workflow-processed').should('exist'); // vérifie que l'étape finale est bien active
  });
});
