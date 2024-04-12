package tn.esprit.twin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Reservation findByIdReservation(long id);
    List<Reservation> findByAnneeUniversitaireBetween(Date b, Date f);

    List<Reservation> findByChambre_NumeroChambreAndEstValideAndAnneeUniversitaireBetween(long cc,Boolean valide , Date dateS , Date dateE);

    List<Reservation> findByEstValideOrAndAnneeUniversitaire( boolean estValide, Date anneeUniversitaire);
}
