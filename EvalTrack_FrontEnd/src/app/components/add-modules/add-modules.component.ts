import { Component, OnInit } from '@angular/core';
import { ModuleService } from '../../../Services/module.service';
import { MatiereService } from '../../../Services/matiere.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Module } from '../../types';
import { SectionServiceService } from '../../../Services/section-service.service';




interface Filiere {
  id: number;
  nom: string;
}

interface Matiere {
  matiereId?: number;
  nom: string;
  coefficient: number;
  description: string;
  ponderation: string;
  moyenne:number;
}

interface NewModuleForm {
  nom: string;
  nbMatieres: number;
  matieres: Matiere[];
  coefModule: number;
}

@Component({
  selector: 'app-add-modules',
  standalone: true,
  imports: [CommonModule, FormsModule ],
  templateUrl: './add-modules.component.html',
  styleUrls: ['./add-modules.component.scss']
})
export class AddModulesComponent implements OnInit {
  //enaaaaaaaaaaaaaaaaaaa
  sections: any[] = []
  modules: any[] = []
   idModule=0;
   idSection=0;
   Semestre=0;
   successMessage: string = '';
   errorMessage = '';
  semestres = ['Semestre 1', 'Semestre 2', 'Semestre 3', 'Semestre 4', 'Semestre 5'];
  
  selectedFiliere: any = this.sections[0];
  selectedSemestre: string = this.semestres[0];
  
  currentView: 'main' | 'add' | 'edit' | 'delete' = 'main';
  actionType: 'addModule' | 'addMatiere' | 'editModule' | 'editMatiere' | 'deleteModule' | 'deleteMatiere' | null = null;
  existingModules: Module[] = [];
  matieresForEdit: Matiere[] = [];
  
  newModule: NewModuleForm = {
    nom: '',
    nbMatieres: 1,
    matieres: [],
    coefModule: 0
  };
  onModuleNameChange(): void {
    // Vous pouvez ajouter ici une logique de validation si nécessaire
    console.log('Nom du module modifié:', this.moduleToEdit?.nomModule);
  }

  loadMatieresForDelete(): void {
    if (!this.selectedModuleForMatiereDelete) {
      this.selectedMatiereToDelete = null;
      return;
    }
  
    // Chargez les matières depuis l'API plutôt que depuis existingModules
    this.matiereService.getMatieres(this.selectedModuleForMatiereDelete).subscribe({
      next: (matieres) => {
        this.matieresForEdit = matieres;
        
        // Mettez à jour également le module correspondant dans existingModules
        const moduleIndex = this.existingModules.findIndex(m => m.idModule === this.selectedModuleForMatiereDelete);
        if (moduleIndex !== -1) {
          this.existingModules[moduleIndex].listeMatieres = [...matieres];
        }
      },
      error: (err) => {
        console.error('Erreur chargement matières:', err);
        this.matieresForEdit = [];
      }
    });
  }
  
  // Fonction pour obtenir les matières pour la suppression
  getMatieresForDelete(moduleId: number): Matiere[] {
    // Utilisez matieresForEdit si disponible, sinon chargez depuis existingModules
    if (this.selectedModuleForMatiereDelete === moduleId && this.matieresForEdit.length > 0) {
      return this.matieresForEdit;
    }
    
    const module = this.existingModules.find(m => m.idModule === moduleId);
    return module?.listeMatieres || [];
  }
  
  // Fonction pour charger les détails d'une matière pour la suppression
  loadMatiereDetails(): void {
    // Cette fonction est appelée lors de la sélection d'une matière à supprimer
    // Vous pouvez ajouter des logs pour le débogage si nécessaire
    console.log('Matière sélectionnée pour suppression:', this.selectedMatiereToDelete);
  }
  selectedModuleForMatiere: number =0;
  newMatiere: Omit<Matiere, 'idMatiere'> = { 
    nom: '', 
    coefficient: 1, 
    description: '', 
    ponderation: 'Examen',
    moyenne:0
  };
  
  selectedModuleToEdit: number | null = null;
  moduleToEdit: Partial<Module> | null = null;
  
  selectedModuleForMatiereEdit: number | null = null;
  selectedMatiereToEdit: number | null = null;
  matiereToEdit: Partial<Matiere> | null = null;
  
  selectedModuleToDelete: number | null = null;
  selectedModuleForMatiereDelete: number | null = null;
  selectedMatiereToDelete: number | null = null;
  moduleDetails: Module | null = null;

  constructor(
    private moduleService: ModuleService,
    private matiereService: MatiereService,
    private sectionService :SectionServiceService
  ) {
    this.initNewMatieres();
  }

  ngOnInit(): void {
   
    this.sectionService.getSections().subscribe({
      next: (data) => {
        console.log('Sections received:', data);

        this.sections = data.map((section) => ({
          idSection: section.idSection,
          nomSection: section.nomSection,
        }));
      },
      error: (err) => {
        console.error('Error fetching sections:', err);
      },
    });
  }
  onModuleChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const selectedModuleForMatiere = target.value;
    
      this.idModule = parseInt( selectedModuleForMatiere);
    }
  }
  onSectionChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const selectedSection = target.value;
    
      this.idSection = parseInt( selectedSection);
      if (this.Semestre) {
        this.loadModulesForCurrentSectionAndSemester();
    }
    }
  }
  onSemestreChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;

    if (target) {
      const lastChar = this.selectedSemestre.slice(-1);
       this.Semestre = parseInt(lastChar);
       if (this.Semestre) {
        this.loadModulesForCurrentSectionAndSemester();
    }
  }
  }
 
  AddMatiereToModule() {
    if (!this.isNewMatiereValid()) return;
  
    const matiereToSend = {
      nom: this.newMatiere.nom,
      coefficient: this.newMatiere.coefficient,
      description: this.newMatiere.description,
      ponderation: this.newMatiere.ponderation,
      moyenne: this.newMatiere.moyenne,
      module: { idModule: this.idModule }
    };
  
    this.matiereService.addMatiereToModule(matiereToSend, this.idModule).subscribe({
      next: (data) => {
        // Message de succès amélioré
        this.successMessage = `La matière "${this.newMatiere.nom}" a été ajoutée avec succès au module !`;
        
        // Réinitialiser le formulaire
        this.resetForms();
        this.actionType = null; // Retour aux options
        
        setTimeout(() => this.successMessage = '', 5000);
      },
      error: (err) => {
        console.error('Error adding matiere:', err);
        this.errorMessage = "Erreur lors de l'ajout de la matière: " + (err.error?.message || err.message);
        setTimeout(() => this.errorMessage = '', 8000);
      }
    });
  }

  // ========== UTILITY METHODS ==========
  calculateTotalCoefficient(matieres: Matiere[]): number {
    return matieres.reduce((sum, matiere) => sum + (matiere.coefficient || 0), 0);
  }

  updateModuleCoefficient(): void {
    this.newModule.coefModule = this.calculateTotalCoefficient(this.newModule.matieres);
  }

 

  loadModuleToEdit(): void {
    console.log("ID sélectionné:", this.selectedModuleToEdit); // Debug 1
    console.log("Modules disponibles:", this.existingModules); // Debug 2
  
    if (!this.selectedModuleToEdit) {
      this.moduleToEdit = null;
      return;
    }
  
    // Vérification stricte du type et de la valeur
    const module = this.existingModules.find(m => m.idModule == this.selectedModuleToEdit);
    
    if (module) {
      this.moduleToEdit = { ...module };
      console.log("Module trouvé:", this.moduleToEdit); // Debug 3
    } else {
      console.error("Aucun module trouvé avec l'ID:", this.selectedModuleToEdit);
      this.moduleToEdit = null;
    }
  }
  loadMatieresForEdit(): void {
    if (!this.selectedModuleForMatiereEdit) {
      this.matieresForEdit = [];
      return;
    }
    console.log('Chargement matières pour module:', this.selectedModuleForMatiereEdit);

    // Utilisez la méthode getMatieres() du service
    this.matiereService.getMatieres(this.selectedModuleForMatiereEdit).subscribe({
      next: (matieres) => {
        console.log('Réponse API matières:', matieres);
        this.matieresForEdit = matieres;
        console.log('Matières chargées:', this.matieresForEdit);
        
        // Mettez à jour également existingModules pour cohérence
        const moduleIndex = this.existingModules.findIndex(m => m.idModule === this.selectedModuleForMatiereEdit);
        if (moduleIndex !== -1) {
          this.existingModules[moduleIndex].listeMatieres = [...matieres];
        }
      },
      error: (err) => console.error('Erreur chargement matières:', err)
    });
  }

  loadMatiereToEdit(): void {
    if (!this.selectedMatiereToEdit || !this.selectedModuleForMatiereEdit) {
      this.matiereToEdit = null;
      return;
    }

    const module = this.existingModules.find(m => m.idModule === this.selectedModuleForMatiereEdit);
    const matiere = module?.listeMatieres.find(m => m.matiereId === this.selectedMatiereToEdit);
    this.matiereToEdit = matiere ? { ...matiere } : null;
  }

  loadModuleDetails(): void {
    if (!this.selectedModuleToDelete) {
      this.moduleDetails = null;
      return;
    }

    const module = this.existingModules.find(m => m.idModule === this.selectedModuleToDelete);
    this.moduleDetails = module ? { ...module } : null;
  }

  // ========== VIEW MANAGEMENT ==========
  switchView(view: 'main' | 'add' | 'edit' | 'delete'): void {
    this.currentView = view;
    this.actionType = null;
    this.resetForms();
  }

  setActionType(type: 'addModule' | 'addMatiere' | 'editModule' | 'editMatiere' | 'deleteModule' | 'deleteMatiere'): void {
    this.actionType = type;
    this.resetForms();
    
    if (this.idSection && this.Semestre) {
      this.loadModulesForCurrentSectionAndSemester();
    }
 }

 private loadModulesForCurrentSectionAndSemester(): void {
  this.moduleService.getModule(this.idSection, this.Semestre).subscribe({
      next: (data) => {
          if (Array.isArray(data)) {
              this.modules = data.map(module => ({
                  idModule: module.idModule,
                  nomModule: module.nomModule,
              }));
              this.existingModules = data; // Mettez à jour existingModules
          }
      },
      error: (err) => console.error('Error fetching modules:', err)
  });
}

  resetAction(): void {
    this.actionType = 'addModule';
    this.resetForms();
  }

  resetForms(): void {
    this.newModule = { nom: '', nbMatieres: 1, matieres: [], coefModule: 0 };
    this.initNewMatieres();
    this.newMatiere = { nom: '', coefficient: 1, description: '', ponderation: '0.35*DS+0.65Examen' ,moyenne: 0};
    this.moduleToEdit = null;
    this.matiereToEdit = null;
    this.selectedModuleToEdit = null;
    this.selectedModuleForMatiere = 0;
    this.selectedModuleToDelete = null;
    this.selectedModuleForMatiereDelete = null;
    this.selectedMatiereToDelete = null;
    this.moduleDetails = null;
  }

  initNewMatieres(): void {
    this.newModule.matieres = Array(this.newModule.nbMatieres).fill(0).map(() => ({
      nom: '',
      coefficient: 1,
      description: '',
      ponderation: '0.35*DS+0.65Examen',
      moyenne:0
    }));
    this.updateModuleCoefficient();
  }

  // ========== CRUD OPERATIONS ==========
  addNewModule(): void {
    if (!this.isNewModuleValid()) return;
  
    const semestreNumber = this.semestres.indexOf(this.selectedSemestre) + 1;
    
    // Créer l'objet module à envoyer
    const moduleToSend = {
      nomModule: this.newModule.nom,
      moyenne: 0,
      semestre: semestreNumber,
      coefModule: this.calculateTotalCoefficient(this.newModule.matieres),
      section: { idSection: this.idSection }
    };
  
    // Créer la liste des matières à envoyer
    const matieresToSend = this.newModule.matieres.map(matiere => ({
      nom: matiere.nom,
      coefficient: matiere.coefficient,
      description: matiere.description,
      ponderation: matiere.ponderation,
      moyenne: 0
    }));
  
    // Créer l'objet complet pour l'envoi
    const moduleWithMatieres = {
      module: moduleToSend,
      matieres: matieresToSend
    };
  
    this.moduleService.createModuleWithMatieres(moduleWithMatieres).subscribe({
      next: () => {
        this.successMessage = 'Module et matières ajoutés avec succès!';
        this.resetForms();
        this.actionType = null;
        setTimeout(() => this.successMessage = '', 5000);
      },
      error: (error) => {
        console.error('Erreur:', error);
        this.errorMessage = "Erreur lors de l'ajout du module et des matières";
        setTimeout(() => this.errorMessage = '', 8000);
      }
    });
  }

  addNewMatiere(): void {
    if (!this.isNewMatiereValid()) return;

    const matiereToSend = {
      ...this.newMatiere,
      module: { idModule: this.selectedModuleForMatiere }
    };

    this.matiereService.addMatiere(matiereToSend).subscribe({
      next: () => this.handleSuccess('Matière ajoutée avec succès!'),
      error: (error: any) => this.handleError('Erreur lors de l\'ajout de la matière', error)
    });
  }

  saveModuleChanges(): void {
    if (!this.isModuleFormValid()) {
      alert('Veuillez remplir correctement le formulaire');
      return;
    }
  
    const moduleToUpdate: Partial<Module> = {
      nomModule: this.moduleToEdit?.nomModule?.trim(),
      semestre: this.moduleToEdit?.semestre,
      coefModule: this.moduleToEdit?.coefModule,
      section: this.moduleToEdit?.section
    };
  
    this.moduleService.updateModule(this.selectedModuleToEdit!, moduleToUpdate).subscribe({
      next: (updatedModule) => {
        this.successMessage = 'Module modifié avec succès !';
        // Mettre à jour la liste des modules
        const index = this.existingModules.findIndex(m => m.idModule === this.selectedModuleToEdit);
        if (index !== -1) {
          this.existingModules[index] = { ...this.existingModules[index], ...updatedModule };
        }
        this.resetForms();
        this.actionType = null;
        // Retour à la vue principale après 2 secondes
        setTimeout(() => { this.successMessage = ''; }, 4000);
      },
      error: (error) => {
        console.error('Erreur:', error);
        this.errorMessage = "Erreur lors de la modification du module";
        setTimeout(() => this.errorMessage = '', 5000);
      }
    });
  }

  // Dans add-modules.component.ts
  saveMatiereChanges(): void {
    if (!this.canSaveMatiere()) {
      alert('Veuillez remplir tous les champs correctement');
      return;
    }
  
    const matiereToUpdate = {
      nom: this.matiereToEdit?.nom,
      coefficient: this.matiereToEdit?.coefficient,
      description: this.matiereToEdit?.description,
      ponderation: this.matiereToEdit?.ponderation,
      moyenne: this.matiereToEdit?.moyenne || 0,
      module: { idModule: this.selectedModuleForMatiereEdit }
    };
  
    this.matiereService.updateMatiere(this.selectedMatiereToEdit!, matiereToUpdate).subscribe({
      next: () => {
        this.successMessage = 'Matière modifiée avec succès !';
        // Retour à la vue principale après 2 secondes
        this.resetForms();
        this.actionType = null;
        setTimeout(() => {this.successMessage = '';}, 4000);
      },
      error: (error) => {
        console.error('Erreur:', error);
        this.errorMessage = `Erreur lors de la modification: ${error.message}`;
        setTimeout(() => this.errorMessage = '', 5000);
      }
    });
  }

  confirmDeleteModule(): void {
    if (!this.selectedModuleToDelete) return;

    this.moduleService.deleteModule(this.selectedModuleToDelete).subscribe({
      next: () => {this.successMessage = 'Module supprimé avec succès !';
      this.resetForms();
        this.actionType = null;
        setTimeout(() => {this.successMessage = '';}, 4000);
      },
      error: (error: any) => this.handleError('Erreur lors de la suppression du module', error)
    });
  }

  confirmDeleteMatiere(): void {
    if (!this.selectedMatiereToDelete) return;
  
    if (confirm('Êtes-vous sûr de vouloir supprimer cette matière ?')) {
      this.matiereService.deleteMatiere(this.selectedMatiereToDelete).subscribe({
        next: () => {
          this.successMessage = 'Matière supprimée avec succès!';
          
          // Recharger les matières après suppression
          this.loadMatieresForDelete();
          this.selectedMatiereToDelete = null;
          this.resetForms();
          this.actionType = null;
          setTimeout(() => {this.successMessage = '';}, 4000);
        },
        error: (error) => {
          console.error('Erreur:', error);
          this.errorMessage = "Erreur lors de la suppression de la matière";
          setTimeout(() => this.errorMessage = '', 8000);
        }
      });
    }
  }

  // ========== VALIDATION METHODS ==========
  private isNewModuleValid(): boolean {
    if (!this.newModule.nom || this.newModule.matieres.some(m => !m.nom)) {
      alert('Veuillez remplir tous les champs obligatoires');
      return false;
    }
    return true;
  }

  private isNewMatiereValid(): boolean {
    if (!this.selectedModuleForMatiere || !this.newMatiere.nom) {
      alert('Veuillez sélectionner un module et remplir le nom de la matière');
      return false;
    }
    return true;
  }

  public isModuleFormValid(): boolean {
    return !!this.selectedModuleToEdit && !!this.moduleToEdit?.nomModule?.trim();
  }

  canSaveMatiere(): boolean {
    return !!this.matiereToEdit?.nom?.trim() && 
           this.matiereToEdit?.coefficient !== undefined &&
           this.matiereToEdit?.coefficient > 0 &&
           !!this.matiereToEdit?.description?.trim() &&
           !!this.matiereToEdit?.ponderation?.trim() &&
           !!this.selectedMatiereToEdit;
  }

  // ========== HELPER METHODS ==========
  private handleSuccess(message: string): void {
    alert(message);
   
    this.resetAction();
    this.switchView('main');
  }

  private handleError(message: string, error: any): void {
    console.error(message, error);
    alert(message);
  }

  getMatieresForModule(moduleId: number): Matiere[] {
    // Si matieresForEdit est déjà chargé, utilisez-le
    if (this.selectedModuleForMatiereEdit === moduleId && this.matieresForEdit.length > 0) {
      return this.matieresForEdit;
    }
    
    // Sinon, cherchez dans existingModules
    const module = this.existingModules.find(m => m.idModule === moduleId);
    return module?.listeMatieres || [];
  }

  getModuleName(moduleId: number): string {
    const module = this.existingModules.find(m => m.idModule === moduleId);
    return module ? module.nomModule : 'Inconnu';
  }

  getMatiereName(matiereId: number | null): string {
    if (!matiereId || !this.selectedModuleForMatiereDelete) return 'Inconnu';
    const module = this.existingModules.find(m => m.idModule === this.selectedModuleForMatiereDelete);
    const matiere = module?.listeMatieres.find(m => m.matiereId === matiereId);
    return matiere ? matiere.nom : 'Inconnu';
  }

  getMatiereCoefficient(matiereId: number | null): number {
    if (!matiereId || !this.selectedModuleForMatiereDelete) return 0;
    const module = this.existingModules.find(m => m.idModule === this.selectedModuleForMatiereDelete);
    const matiere = module?.listeMatieres.find(m => m.matiereId === matiereId);
    return matiere ? matiere.coefficient : 0;
  }

  getMatiereDescription(matiereId: number | null): String {
    if (!matiereId || !this.selectedModuleForMatiereDelete) return '';
    const module = this.existingModules.find(m => m.idModule === this.selectedModuleForMatiereDelete);
    const matiere = module?.listeMatieres.find(m => m.matiereId === matiereId);
    return matiere ? matiere.description :'' ;
  }

  getMatiereMoyenne(matiereId: number | null): number {
    if (!matiereId || !this.selectedModuleForMatiereDelete) return 0;
    const module = this.existingModules.find(m => m.idModule === this.selectedModuleForMatiereDelete);
    const matiere = module?.listeMatieres.find(m => m.matiereId === matiereId);
    return matiere ? matiere.moyenne :0 ;
  }

  getMatierePonderation(matiereId: number | null): String {
    if (!matiereId || !this.selectedModuleForMatiereDelete) return '';
    const module = this.existingModules.find(m => m.idModule === this.selectedModuleForMatiereDelete);
    const matiere = module?.listeMatieres.find(m => m.matiereId === matiereId);
    return matiere ? matiere.ponderation :'' ;
  }
}