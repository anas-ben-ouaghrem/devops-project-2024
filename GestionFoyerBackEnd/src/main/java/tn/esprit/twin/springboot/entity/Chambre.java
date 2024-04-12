package tn.esprit.twin.springboot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table( name = "Chambre")
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idChambre")
    private long  idChambre; // Clé primaire
    @Enumerated(EnumType.ORDINAL)
    private TypeChambre typeC;
    private Integer numeroChambre;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "chambre")
    @JsonProperty("reservations")
    private Set<Reservation> reservations;

    @ManyToOne
    Bloc bloc;

}
