import { Bloc } from 'src/app/models/bloc';
import { reservation } from './reservation';
//import { Reservation } from './reservation.model';
export class Chambre {
  idChambre!: number;
  numeroChambre!: number;
  type!: string;
  blocch!: Bloc;
  reservationsChambre!: reservation[];
}