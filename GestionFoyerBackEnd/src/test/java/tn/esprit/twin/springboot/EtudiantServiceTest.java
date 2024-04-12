package tn.esprit.twin.springboot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import tn.esprit.twin.springboot.entity.Etudiant;
import tn.esprit.twin.springboot.entity.Reservation;
import tn.esprit.twin.springboot.repository.EtudiantRepository;
import tn.esprit.twin.springboot.repository.ReservationRepository;
import tn.esprit.twin.springboot.service.EtudiantService;

@ActiveProfiles("test")
@SpringBootTest
class EtudiantServiceTest {

    @MockBean
    private EtudiantRepository etudiantRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private EtudiantService etudiantService;

    private Etudiant etudiant;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        reservation = new Reservation();
        // Initialize your etudiant and reservation with test data
    }

    @Test
    void retrieveAllEtudiantsTest() {
        when(etudiantRepository.findAll()).thenReturn(Collections.singletonList(etudiant));
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();
        assertFalse(result.isEmpty());
        verify(etudiantRepository).findAll();
    }

    @Test
    void addEtudiantTest() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);
        Etudiant result = etudiantService.addEtudiant(new Etudiant());
        assertNotNull(result);
        verify(etudiantRepository).save(any(Etudiant.class));
    }

    @Test
    void updateEtudiantTest() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);
        Etudiant result = etudiantService.updateEtudiant(new Etudiant());
        assertNotNull(result);
        verify(etudiantRepository).save(any(Etudiant.class));
    }

    @Test
    void retrieveEtudiantTest() {
        when(etudiantRepository.findById(anyLong())).thenReturn(Optional.of(etudiant));
        Etudiant result = etudiantService.retrieveEtudiant(1L);
        assertNotNull(result);
        verify(etudiantRepository).findById(anyLong());
    }

    @Test
    void removeEtudiantTest() {
        doNothing().when(etudiantRepository).deleteById(anyLong());
        etudiantService.removeEtudiant(1L);
        verify(etudiantRepository).deleteById(anyLong());
    }

    @Test
    void rechercherEtudiantTest() {
        when(etudiantRepository.findByNomEtOrPrenomEtOrCinOrEcole(anyString(), anyString(), anyLong(), anyString())).thenReturn(Collections.singletonList(etudiant));
        List<Etudiant> result = etudiantService.rechercherEtudiant("Test", "Test", 12345678L, "Test");
        assertFalse(result.isEmpty());
        verify(etudiantRepository).findByNomEtOrPrenomEtOrCinOrEcole(anyString(), anyString(), anyLong(), anyString());
    }
}
