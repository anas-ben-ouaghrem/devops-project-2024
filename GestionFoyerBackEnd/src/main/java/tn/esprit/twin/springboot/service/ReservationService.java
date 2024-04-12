package tn.esprit.twin.springboot.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot.entity.Chambre;
import tn.esprit.twin.springboot.entity.Reservation;
import tn.esprit.twin.springboot.repository.ChambreRepository;
import tn.esprit.twin.springboot.repository.ReservationRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {
    ReservationRepository res;
    ChambreRepository cr;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return res.findAll();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return res.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        Reservation reservation = res.findByIdReservation(r.getIdReservation());
        reservation.setAnneeUniversitaire(r.getAnneeUniversitaire());

        return res.save(reservation);
    }

    @Override
    public void removeReservation(Long id) {
        res.deleteById(id);
    }

    @Override
    public Reservation retrieveReservation(long idRes) {

        return res.findByIdReservation(idRes);
    }

    public List<Reservation> getReservationParAnneeUniversitaire(Date dateDebut, Date dateFin) {

        List<Reservation> allReservations = res.findByAnneeUniversitaireBetween(dateDebut, dateFin);
        return allReservations;

    }

    @Override
    public List<Reservation> rechercherRes(boolean estValide, Date anneeUniversitaire) {
        return res.findByEstValideOrAndAnneeUniversitaire(estValide, anneeUniversitaire);
    }

@Override
    public byte[] generateQrCodeImage(String content, int width, int height) throws IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hintsMap = new HashMap<>();
        hintsMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hintsMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate QR code image.", e);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedImage qrImage = toBufferedImage(bitMatrix);
        try {
            ImageIO.write(qrImage, "png", outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write QR code image to output stream.", e);
        }

        return outputStream.toByteArray();
    }

    @Override
    public BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (matrix.get(x, y)) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }
        return image;
    }
    public Reservation affecterReservationAchambre (long idres, long numeroChambre)
    {

        Chambre c = cr.findByNumeroChambre(numeroChambre);
        Reservation rr = res.findByIdReservation(idres);
            rr.setChambre(c);

            res.save(rr);

return rr;
    }


}
