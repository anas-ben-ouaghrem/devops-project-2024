package tn.esprit.twin.springboot.service;

import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot.entity.Etudiant;

import java.util.List;


public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();

    Etudiant addEtudiant(Etudiant e);

    Etudiant updateEtudiant(Etudiant e);

    Etudiant retrieveEtudiant(Long idEtudiant);

    void removeEtudiant(Long idEtudiant);
}
