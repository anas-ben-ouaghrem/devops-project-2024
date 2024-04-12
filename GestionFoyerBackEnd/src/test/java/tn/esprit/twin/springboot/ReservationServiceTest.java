package tn.esprit.twin.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.twin.springboot.entity.Chambre;
import tn.esprit.twin.springboot.entity.Reservation;
import tn.esprit.twin.springboot.repository.ChambreRepository;
import tn.esprit.twin.springboot.repository.ReservationRepository;
import tn.esprit.twin.springboot.service.ReservationService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class ReservationServiceTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private ChambreRepository chambreRepository;

    @Autowired
    private ReservationService reservationService;

    private Reservation reservation;
    private Chambre chambre;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        chambre = new Chambre();
        // Initialize your reservation and chambre with test data
    }

    @Test
    void retrieveAllReservationsTest() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));
        List<Reservation> result = reservationService.retrieveAllReservations();
        assertFalse(result.isEmpty());
        verify(reservationRepository).findAll();
    }

    @Test
    void addReservationTest() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        Reservation result = reservationService.addReservation(new Reservation());
        assertNotNull(result);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void updateReservationTest() {
        when(reservationRepository.findByIdReservation(anyLong())).thenReturn(reservation);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        Reservation updated = new Reservation();
        updated.setIdReservation(1L);
        Reservation result = reservationService.updateReservation(updated);
        assertNotNull(result);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void removeReservationTest() {
        doNothing().when(reservationRepository).deleteById(anyLong());
        reservationService.removeReservation(1L);
        verify(reservationRepository).deleteById(anyLong());
    }

    @Test
    void retrieveReservationTest() {
        when(reservationRepository.findByIdReservation(anyLong())).thenReturn(reservation);
        Reservation result = reservationService.retrieveReservation(1L);
        assertNotNull(result);
        verify(reservationRepository).findByIdReservation(anyLong());
    }

    @Test
    void generateQrCodeImageTest() throws IOException {
        byte[] imageData = reservationService.generateQrCodeImage("Test", 100, 100);
        assertNotNull(imageData);
        assertTrue(imageData.length > 0);
    }

    @Test
    void affecterReservationAchambreTest() {
        when(chambreRepository.findByNumeroChambre(anyLong())).thenReturn(chambre);
        when(reservationRepository.findByIdReservation(anyLong())).thenReturn(reservation);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.affecterReservationAchambre(1L, 101L);
        assertNotNull(result);
        assertEquals(chambre, result.getChambre());
        verify(reservationRepository).save(any(Reservation.class));
    }
}
