package tn.esprit.twin.springboot.service;

import com.google.zxing.common.BitMatrix;
import tn.esprit.twin.springboot.entity.Etudiant;
import tn.esprit.twin.springboot.entity.Reservation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();

    Reservation addReservation(Reservation r);

    Reservation updateReservation(Reservation r);


    void removeReservation(Long id);


    Reservation retrieveReservation(long idRes);

    List<Reservation>rechercherRes(boolean estValide, Date anneeUniversitaire);

    byte[] generateQrCodeImage(String content, int width, int height) throws IOException;

    BufferedImage toBufferedImage(BitMatrix matrix);


}
