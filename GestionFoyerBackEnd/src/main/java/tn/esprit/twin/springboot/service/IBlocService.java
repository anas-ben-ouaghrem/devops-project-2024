package tn.esprit.twin.springboot.service;

import tn.esprit.twin.springboot.entity.Bloc;

import java.util.List;

public interface IBlocService {
    List<Bloc> retrieveBlocs();

    Bloc updateBloc(Bloc bloc);

    Bloc addBloc(Bloc bloc);

    void removeBloc(Long idBloc);

    Bloc retrieveBloc(long idBloc);

    void archiverBloc(long idBloc);


    public long getNumChambreAffected(long blocId);
    public byte[] exportBlocToPdf(Bloc bloc);

    public Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc) ;
}

