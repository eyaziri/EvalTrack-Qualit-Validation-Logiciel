describe('Test E2E - Connexion', () => {
  it('devrait se connecter et accéder au tableau de bord', () => {
    // 1️⃣ Ouvrir la page
    cy.visit('http://localhost:4200/login')  // ton URL locale ou déployée

    // 2️⃣ Remplir les champs
    cy.get('input[name="email"]').type('test@example.com')
    cy.get('input[name="password"]').type('password123')

    // 3️⃣ Soumettre le formulaire
    cy.get('button[type="submit"]').click()

    // 4️⃣ Vérifier la redirection et le contenu
   
     cy.url().should('include', '/etudiant-page-accueil')


    cy.contains('Bienvenue').should('be.visible')
  })
})
