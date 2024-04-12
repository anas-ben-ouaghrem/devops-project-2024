package tn.esprit.twin.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Etudiant;
import tn.esprit.twin.springboot.entity.Reservation;
import tn.esprit.twin.springboot.service.BlocService;
import tn.esprit.twin.springboot.service.ChamberService;
import tn.esprit.twin.springboot.service.IChamberService;
import tn.esprit.twin.springboot.service.ReservationService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4201", "http://localhost:4200"})
@RestController
@RequestMapping("reservation/")
public class ReservationController {
    @Autowired
    ReservationService R;
    @Autowired
    ChamberService C;

    @GetMapping("get")
    public List<Reservation> getAllReservations() {
        return R.retrieveAllReservations();
    }

    @PostMapping("addReservation")
    public  Reservation addRes(@RequestBody Reservation r) {
        Reservation reservation = R.addReservation(r);
        return reservation;
    }

    @DeleteMapping("removeRes/{reservation-id}")
    public void removeRes(@PathVariable("reservation-id") long resId) {
        R.removeReservation(resId);
    }

    @GetMapping("getReservationbyId/{resId}")
    public Reservation retrieveRes(@PathVariable("resId") long resId) {

        return R.retrieveReservation(resId);
    }

    @PutMapping("updatereservation")
    public  Reservation updateRes(@RequestBody Reservation r) {

        Reservation reservation = R.updateReservation(r);
        return reservation;
    }
    @GetMapping("statistique")
    public Map<String, Long> retrieveRes() {
        Map<String, Long> cc = C.pourcentageChambreParTypeChambre();
        return cc;
    }
    @GetMapping("/rechercherRes")
    public List<Reservation> rechercherRes(
            @RequestParam( required = false) boolean estValide,
            @RequestParam( required = false) Date anneeUniversitaire){

        return R.rechercherRes(estValide,anneeUniversitaire);
    }

    @GetMapping(value = "/qrcode/{content}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQrCode(@PathVariable String content) throws IOException {
        int width = 200;
        int height = 200;
        return R.generateQrCodeImage(content, width, height);
    }

    @PostMapping("/affecte-chambres-res/{idres}/{numcham}")
    public Reservation affecterChambresABloc (@PathVariable("idres") long idres , @PathVariable("numcham") long numChambre ){
        return R.affecterReservationAchambre(idres,numChambre);
    }

}
