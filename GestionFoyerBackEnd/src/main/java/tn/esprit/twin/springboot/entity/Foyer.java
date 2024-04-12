package tn.esprit.twin.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table( name = "Foyer")
public class Foyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idFoyer")
    private long  idFoyer; // Clé primaire
    private String nomFoyer;
    private long capacityFoyer;
    private boolean archived = false;

    public boolean isArchived() {
        return archived;
    }

    @OneToOne(mappedBy = "foyer")
    private Universite universite;

    @OneToMany(mappedBy = "foyer" , cascade = CascadeType.ALL)
    private List<Bloc> bloc;

}
