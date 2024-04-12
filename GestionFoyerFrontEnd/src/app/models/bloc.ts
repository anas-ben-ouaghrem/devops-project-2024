import { Chambre } from 'src/app/models/chambre';
export class Bloc {
  idBloc!: number;
  nomBloc!: string;
  capaciteBloc!: number;
  archived!: boolean;
  foyerr!: any;
  chambres!: Chambre[];
}