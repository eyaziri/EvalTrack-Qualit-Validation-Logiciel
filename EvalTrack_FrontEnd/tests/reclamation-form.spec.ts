import { test, expect } from '@playwright/test';

test.describe('Formulaire de réclamation', () => {

  test.beforeEach(async ({ page }) => {
    // Simule un étudiant connecté dans le localStorage
    await page.addInitScript(() => {
      window.localStorage.setItem('idRole', '2');
      window.localStorage.setItem('idUser', '138');
    });

    // Ouvre la page de réclamation (à adapter selon ton app)
    await page.goto('http://localhost:4200/etudiant-page-reclamation');
  });

  test('Affiche le titre du formulaire', async ({ page }) => {
    await expect(page.getByText('Soumettre une reclamation')).toBeVisible();
  });

  test('Permet de remplir et soumettre le formulaire', async ({ page }) => {
    // Interception de la requête POST d’ajout d’une réclamation
    await page.route('http://localhost:8080/EvalTrack/api/reclamations', async route => {
      const json = {
        id: 25,
        type: 'ERREUR_NOTE_DS',
        matiereConcerne: 'Programmation Orientée Objet',
        nomProf: 'Sana Nouira',
        statut: 'EN_COURS',
        dateCreation: new Date().toISOString(),
        etudiant: { idEtudinat: 138 },
        administrateur: { id: 4 }
      };
      await route.fulfill({ status: 201, contentType: 'application/json', body: JSON.stringify(json) });
    });

    // Sélection du type de réclamation
    await page.selectOption('select[name="type"]', 'ERREUR_NOTE_DS');
    // Remplir les champs
    await page.fill('input[name="matiereConcerne"]', 'Programmation Orientée Objet');
    await page.fill('input[name="nomProf"]', 'Sana Nouira');
    // Soumettre
    await page.click('button[type="submit"]');
    
    
  });

  
});
