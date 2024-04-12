package tn.esprit.twin.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table( name = "Bloc")
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBloc")
    private long idBloc; // Clé primaire
    private String nomBloc;
    private long capacityBloc;
    private boolean Archived=false;
    @OneToMany(cascade = CascadeType.ALL)
    List<Chambre> chambers;

    @ManyToOne(cascade = CascadeType.ALL)
    Foyer foyer;

}
