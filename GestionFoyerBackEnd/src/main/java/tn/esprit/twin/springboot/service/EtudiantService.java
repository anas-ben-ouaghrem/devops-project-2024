package tn.esprit.twin.springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Chambre;
import tn.esprit.twin.springboot.entity.Etudiant;
import tn.esprit.twin.springboot.entity.Reservation;
import tn.esprit.twin.springboot.repository.EtudiantRepository;
import tn.esprit.twin.springboot.repository.ReservationRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class EtudiantService implements IEtudiantService{

    EtudiantRepository etudiantRepository;
    ReservationRepository res;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).get();
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }


    public Etudiant affecterEtudiantAReservation (String nomEt, String prenomEt, long  idReservation) {
        Etudiant ee = etudiantRepository.findByNomEtAndPrenomEt(nomEt ,prenomEt );
        Set<Reservation> le = new HashSet<>();
        le = ee.getReservationset();

        Reservation rr = res.findByIdReservation(idReservation);
        le.add(rr);


        ee.setReservationset(le);

        return etudiantRepository.save(ee);
    }

    public List<Etudiant>rechercherEtudiant(String nomEt, String prenomEt, Long cin, String ecole){
        return etudiantRepository.findByNomEtOrPrenomEtOrCinOrEcole(nomEt,prenomEt,cin,ecole);
    }

    public Map<String, String> Statistiques() {
        long countTotal = etudiantRepository.count();
        List<Etudiant> etudiants = etudiantRepository.findByReservationset_EstValide(true);
        long countValide = (etudiants.stream().count() * 100) / countTotal;

        List<Etudiant> etudiant = etudiantRepository.findByReservationset_EstValide(false);
        long countNonValide = (etudiant.stream().count() * 100) / countTotal;

        Map<String, String> statistiques = new HashMap<>();

        statistiques.put("countReservation", countValide + "%");
        statistiques.put("countNonReservation", countNonValide + "%");
        statistiques.put("countTotal", countTotal + "");

        return statistiques;


    }

}
