package tn.esprit.twin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    

    Etudiant findByNomEtAndPrenomEt(String nomEt, String prenomEt);



    List<Etudiant> findByNomEtOrPrenomEtOrCinOrEcole(String nomEt, String prenomEt, Long cin, String ecole);

    List<Etudiant>findByReservationset_EstValide(boolean v);


}
