package tn.esprit.twin.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Data
@Table( name = "Universite")
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUniversite")
    private long  idUniversite; // Clé primaire
    private String nomUniversite;
    private String adresse;

    @OneToOne
    public Foyer foyer;
}
