package tn.esprit.twin.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot.entity.Foyer;
import tn.esprit.twin.springboot.service.IFoyerService;

import java.util.List;


@RestController
@RequestMapping("/api/foyer")
public class FoyerController {
    @Autowired
    IFoyerService foyerService;
  /*@Autowired
    private JavaMailSender emailSender;

    @PostMapping("/envoyeremail")
    public ResponseEntity<Foyer> createFoyer(@Valid @RequestBody Foyer foyer) {
        Foyer result = foyerService.save(foyer);
        sendEmail(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    private void sendEmail(Foyer foyer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gmail.com");
      message.setTo("admin@gmail.com");
        message.setSubject("Foyer Created Successfully");
        message.setText("A new foyer with name " + foyer.getNomFoyer() + " has been created.");
        emailSender.send(message);
    }*/

    @GetMapping("/get")
    public List<Foyer> getFoyers() {
        return foyerService.retrieveAllFoyers();

    }

    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long foyerId) {
        return foyerService.retrieveFoyer(foyerId);
    }

    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer e) {
        Foyer foyer = foyerService.addFoyer(e);
        return foyer;
    }

    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long foyerId) {
        foyerService.removeFoyer(foyerId);
    }

    @PutMapping("/update-foyer")
    public Foyer updateFoyer(@RequestBody Foyer e) {

        Foyer foyer = foyerService.updateFoyer(e);
        return foyer;
    }
    @PostMapping ("/capacitefoyer/{idd}")
    public Long capacite(@PathVariable ("idd")  long idfoyer)
    {
        return  foyerService.capacitemaximale(idfoyer) ;
    }

    @GetMapping("/{id}/export/pdf")
    public ResponseEntity<byte[]> exportFoyerToPdf(@PathVariable long id) {
        Foyer foyer = foyerService.retrieveFoyer(id);

        byte[] pdfBytes = foyerService.exportFoyerToPdf(foyer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "bloc-export.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }



}
