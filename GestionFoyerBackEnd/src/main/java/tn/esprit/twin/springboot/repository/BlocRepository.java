package tn.esprit.twin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.twin.springboot.entity.Bloc;

import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nomBloc);
    @Query(" select b from Bloc b where b.nomBloc = :nomb" )
    Bloc getblocbyNom(@Param("nomb") String nomb);

    List<Bloc> findByFoyer_IdFoyer(long idfoyer);
}
