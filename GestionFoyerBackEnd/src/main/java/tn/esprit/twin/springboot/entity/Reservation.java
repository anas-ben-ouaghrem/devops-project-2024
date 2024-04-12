package tn.esprit.twin.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDate;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table( name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idReservation")
    private Long  idReservation; // Clé primaire


    private boolean estValide;
    @Temporal(TemporalType.DATE)
    private Date anneeUniversitaire;

    @ManyToMany(cascade = CascadeType.ALL , mappedBy = "reservationset")
    List<Etudiant> etudiants;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("reservations")
    private Chambre chambre;




}
