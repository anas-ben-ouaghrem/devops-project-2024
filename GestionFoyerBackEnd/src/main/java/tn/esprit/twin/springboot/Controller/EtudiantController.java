package tn.esprit.twin.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot.entity.Etudiant;
import tn.esprit.twin.springboot.service.BlocService;
import tn.esprit.twin.springboot.service.EtudiantService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/Etudiant")
public class EtudiantController {
    @Autowired
    EtudiantService Es;

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getAllEtudiants() {
        return Es.retrieveAllEtudiants();
    }

    @PostMapping("/add-etudiant")
    public  Etudiant addEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = Es.addEtudiant(e);
        return etudiant;
    }
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") long etudiantId) {
        return Es.retrieveEtudiant(etudiantId);
    }

    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") long etudiantId) {
        Es.removeEtudiant(etudiantId);
    }

    @PutMapping("/update-etudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = Es.updateEtudiant(e);
        return etudiant;
    }

    @GetMapping("/rechercher-etudiants")
    public List<Etudiant> rechercherEtudiant(
            @RequestParam( required = false) String nomEt,
            @RequestParam( required = false) String prenomEt,
            @RequestParam( required = false) Long cin,
            @RequestParam( required = false) String ecole){

        return Es.rechercherEtudiant(nomEt,prenomEt,cin,ecole);
    }
    @GetMapping("/Statistiques")
    public Map<String, String> Statistiques(){
        return Es.Statistiques();
    }





}

