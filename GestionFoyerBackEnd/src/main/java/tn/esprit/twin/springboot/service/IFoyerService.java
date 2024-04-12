package tn.esprit.twin.springboot.service;

import tn.esprit.twin.springboot.entity.Foyer;

import java.util.List;

public interface IFoyerService {


    List<Foyer> retrieveAllFoyers();

    Foyer addFoyer(Foyer e);


    Foyer updateFoyer(Foyer f);

    Foyer retrieveFoyer(Long idFoyer);

    Long  capacitemaximale(long idfoyer);

    void archiverFoyer(long idFoyer);

    void removeFoyer(Long idFoyer);
    byte[] exportFoyerToPdf(Foyer foyer);


}
