


describe('ISTQB E2E Test Suite - Todo App React', () => {

  // Configuration avant chaque test
  beforeEach(() => {
    // 1. On visite l'application (Assurez-vous que le Front tourne sur 3000 et le Back sur 8080)
    cy.visit('http://localhost:3000')
    
    // 2. Attente implicite que les données chargent
    // On vérifie que le titre est bien là pour être sûr que React a fini de monter
    // Utilisation de { matchCase: false } pour gérer la casse CSS vs DOM
    cy.contains('h2', 'Todo List', { matchCase: false }).should('be.visible')
    
    // Pause visuelle initiale pour la clarté de la vidéo
    cy.wait(1000)
  })

  it('TC-01 : Smoke Test & Vérification du "Zombie Item" (Bug #002)', () => {
    // ISTQB : Vérification de l'état initial
    // Selon votre Backend, si la liste est vide, il crée "Click to edit task name"
    // On vérifie que cet élément est présent visuellement
    cy.get('.todoitems')
      .find('input[type="text"]')
      .should('have.value', 'Click to edit task name')
      
    cy.wait(1000) // Pause pour observer l'état initial
  })

  it('TC-02 : Création d\'une Tâche (Functional Testing)', () => {
    // 1. Compter les éléments actuels
    cy.get('.todoitems input[type="text"]').then(($items) => {
      const initialCount = $items.length
      
      // 2. Cliquer sur le bouton "Add task" (Material UI Button)
      cy.contains('button', 'Add task').click()
      cy.wait(500) // Délai pour l'animation d'ajout

      // 3. Vérifier qu'un nouvel input est apparu
      cy.get('.todoitems input[type="text"]').should('have.length', initialCount + 1)
      
      // 4. Vérifier que le dernier élément a le texte par défaut
      cy.get('.todoitems input[type="text"]').last()
        .should('have.value', 'Click to edit task name')
        .click() // Focus pour montrer l'élément actif
        
      cy.wait(1000) // Pause pour valider visuellement
    })
  })

  it('TC-03 : Modification de Tâche (Input & State Update)', () => {
    const newTaskName = 'Test Cypress Automatisé ' + Date.now()

    // 1. Cibler le premier champ texte
    cy.get('.todoitems input[type="text"]').first()
      .clear() // Effacer "Click to edit..."
      .should('have.value', '') // Attendre que le champ soit vide
      .wait(200) // Petite pause pour stabilité React
      .type(newTaskName, { delay: 100 }) // Taper lentement (100ms par touche) pour la démo
      .blur() // Quitter le champ pour déclencher d'éventuels saves

    // 2. Vérifier que la valeur est bien restée (Persistence locale)
    cy.get('.todoitems input[type="text"]').first()
      .should('have.value', newTaskName)
      
    cy.wait(1000) // Pause pour lire le texte saisi
  })

  it('TC-04 : Transition d\'État (Done/Not Done)', () => {
    // 1. Cibler la Checkbox du premier élément
    cy.get('.todoitems input[type="checkbox"]').first().check()
    
    // 2. Vérifier le changement de style visuel (barré)
    cy.get('.todoitems input[type="text"]').first()
      .should('have.class', 'done')
      .and('have.css', 'text-decoration', 'line-through solid rgba(0, 0, 0, 0.3)')
      
    cy.wait(1000) // Pause pour voir l'état "Done"

    // 3. Décocher pour revenir à l'état initial
    cy.get('.todoitems input[type="checkbox"]').first().uncheck()
    
    // 4. Vérifier que le style barré est retiré
    cy.get('.todoitems input[type="text"]').first()
      .should('not.have.class', 'done')
      
    cy.wait(1000) // Pause pour voir l'état "Not Done"
  })

  it('TC-05 : Suppression (Delete) & Confirmation Bug Zombie', () => {
    // 1. Cibler le bouton poubelle via son aria-label 
    cy.get('button[aria-label="delete"]').first().click()

    // 2. Vérification ISTQB du Bug "Zombie"
    // Scénario : Si c'était le seul élément, le backend va le recréer instantanément
    
    cy.wait(1000) // Pause explicite pour laisser le backend répondre et l'UI se mettre à jour

    // Assertion : Si l'élément est toujours là (ou revenu), le bug est confirmé visuellement
    cy.get('.todoitems input[type="text"]').should('exist')
    
    cy.wait(1000) // Pause finale
  })
  
  it('TC-06 : Test de Charge UI (Stress Test)', () => {
     // Ajout rapide de plusieurs tâches pour tester la réactivité
     for(let i = 0; i < 5; i++) {
         cy.contains('button', 'Add task').click()
         cy.wait(200) // Rapide mais perceptible
     }
     
     // Vérification que tous les éléments sont présents
     // Note: le nombre exact dépend de l'état précédent, on vérifie juste l'ajout
     cy.get('.todoitems input[type="text"]').should('have.length.gte', 5)
     
     cy.wait(2000) // Pause longue pour voir la liste remplie
  })

})