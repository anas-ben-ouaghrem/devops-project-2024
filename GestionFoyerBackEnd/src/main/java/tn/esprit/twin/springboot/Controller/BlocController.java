package tn.esprit.twin.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.service.IBlocService;
import tn.esprit.twin.springboot.service.TwilioService;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4201", "http://localhost:4200"})
@RestController
@RequestMapping("bloc")
public class BlocController {
    @Autowired
    IBlocService blocs;

    TwilioService twilioService;


    @GetMapping("/get")
//    @Operation(description = "afficher tous les blocs")
    public List<Bloc> getAllBlocs(){
        return   blocs.retrieveBlocs();
    }

    @PostMapping("/addBloc")
    public  Bloc AjouterBloc ( @RequestBody Bloc b){
        Bloc bloc = blocs.addBloc(b);
        String recipientPhoneNumber = "+21625899398";
        String messageContent = "A new bloc has been added!";
        twilioService.sendSms(recipientPhoneNumber, messageContent);
        return  bloc;

    }
    @PutMapping("/update-bloc")
    public Bloc updateBloc(@RequestBody Bloc e) {
        Bloc bloc = blocs.updateBloc(e);
        return bloc;
    }
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocs.removeBloc(blocId);
    }


    @GetMapping("/getblocbyId/{idbloc}")
    public Bloc getBlocById ( @PathVariable("idbloc")  long idbloc){
        return  blocs.retrieveBloc(idbloc);

    }
    @PutMapping("/archivebloc/{idb}")
    void archiverBloc ( @PathVariable("idb") long idb){
        blocs.archiverBloc (idb);

    }
    @PostMapping("/affecte-chambres-bloc/{nomBloc}/chambre")
    public Bloc affecterChambresABloc (@PathVariable("nomBloc") String nomBloc ,  @RequestBody List<Long> numChambre ){
        return blocs.affecterChambresABloc(numChambre,nomBloc);
    }
    @GetMapping("/{id}/numChambreAffected")
    public Long getNumChambreAffected(@PathVariable("id") long blocId) {
        long numAffected = blocs.getNumChambreAffected(blocId);
        return numAffected;
    }

    @GetMapping("/{id}/export/pdf")
    public ResponseEntity<byte[]> exportBlocToPdf(@PathVariable long id) {
        Bloc bloc = blocs.retrieveBloc(id);

        byte[] pdfBytes = blocs.exportBlocToPdf(bloc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "bloc-export.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
