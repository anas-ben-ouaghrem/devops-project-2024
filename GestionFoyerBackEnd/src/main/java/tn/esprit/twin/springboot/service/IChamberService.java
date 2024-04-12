package tn.esprit.twin.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.twin.springboot.entity.Chambre;
import tn.esprit.twin.springboot.entity.TypeChambre;

import java.util.List;
import java.util.Map;

public interface IChamberService {

    long nbChambreParTypeEtBloc(TypeChambre type, long idBloc);

    List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomFoyer, TypeChambre type);

    @Scheduled(fixedRate = 300000)
    Map<String, Long> pourcentageChambreParTypeChambre();

    @Scheduled(fixedRate = 300000)
    Map<String, Long> nbPlacesDisponibleParChambreAnneeEnCours();

    List<Chambre> retrieveAllChambres();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(long idChambre);

    void removeChambre(Long idChambre);

    List<Chambre> getChambresParNomBloc(String nomBloc);
}
