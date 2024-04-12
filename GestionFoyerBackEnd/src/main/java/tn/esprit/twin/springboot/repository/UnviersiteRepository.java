package tn.esprit.twin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Universite;

import java.util.List;

public interface UnviersiteRepository extends JpaRepository<Universite,Long> {
 Universite findByNomUniversite(String nomUniversite);
}
