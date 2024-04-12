package tn.esprit.twin.springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot.entity.Foyer;
import tn.esprit.twin.springboot.entity.Universite;
import tn.esprit.twin.springboot.repository.FoyerRepository;
import tn.esprit.twin.springboot.repository.UnviersiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteService implements  IUniversiteService{

    UnviersiteRepository unviersiteRepository;
    FoyerRepository fr;
    public List<Universite> retrieveAllUniversities(){
        return unviersiteRepository.findAll();
    };
    public Universite addUniversity (Universite u){
        return unviersiteRepository.save(u);
    };
    public Universite updateUniversity (Universite u){
        return unviersiteRepository.save(u);
    };
    public Universite retrieveUniversity (long idUniversity){
        return unviersiteRepository.findById(idUniversity).get();
    };

    public void removeUniversity  (long idUniversity ){
        unviersiteRepository.deleteById(idUniversity);
    };

    public Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) {

         Foyer  f = fr.findById(idFoyer).get();

        Universite universite = unviersiteRepository.findByNomUniversite(nomUniversite);
        universite.setFoyer(f);
        return unviersiteRepository.save(universite);
    }
    public Universite desaffecterFoyerAUniversite (long idFoyer, long idUniversite)
    {
        Universite universite = retrieveUniversity(idUniversite);
        universite.setFoyer(null);
        return unviersiteRepository.save(universite);
    }


}
