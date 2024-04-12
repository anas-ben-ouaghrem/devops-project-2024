package tn.esprit.twin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Etudiant;
import tn.esprit.twin.springboot.entity.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer,Long> {
    Foyer findByNomFoyer(String nom);


}
