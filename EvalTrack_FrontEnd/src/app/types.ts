// src/app/types.ts
export interface Matiere {
    matiereId?: number;
    nom: string;
    coefficient: number;
    description: string;
    ponderation: string;
    moyenne: number;
  }
  
  export interface Module {
    idModule?: number;
    nomModule: string;
    moyenne: number;
    semestre: number;
    coefModule: number;
    section: { idSection: number };
    listeMatieres: Matiere[];
  }
  
  export interface Filiere {
    id: number;
    nom: string;
  }
  
  export interface NewModuleForm {
    nom: string;
    nbMatieres: number;
    matieres: Matiere[];
    coefModule: number;
  }