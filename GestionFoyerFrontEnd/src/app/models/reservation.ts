import { Chambre } from "./chambre";

export class reservation {
    idReservation!: number;
    estValide!:boolean;
    anneeUniversitaire!: Date;
    //etudiant!: Etudiant[];
    chambre!: Chambre;
   
  }