package tn.esprit.twin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.twin.springboot.entity.Chambre;
import tn.esprit.twin.springboot.entity.TypeChambre;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre,Long> {

    @Query("select c from Chambre c where  c.numeroChambre= :num")
    Chambre getchambrebynum(@Param("num") Long num);


   @Query("SELECT CONCAT( c.numeroChambre, '-', b.nomBloc) FROM Chambre c " +
           "JOIN c.bloc b  where  c.idChambre = :id")
    String concatnumchambreNomBloc(@Param("id") Long id);

    @Query("select count (c) from Chambre c where  c.typeC= :type")
    int  getnbchambrebytype(@Param("type") TypeChambre type);

    Chambre findByNumeroChambre(long num_chamber);

    long countChambresByTypeC(TypeChambre c);



}
