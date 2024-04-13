package tn.esprit.twin.springboot.Controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot.entity.Chambre;
import tn.esprit.twin.springboot.entity.TypeChambre;
import tn.esprit.twin.springboot.service.IChamberService;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("chambre")

public class ChambreController {

    IChamberService chabreservice;

    @GetMapping("/getallchambre")
    public    List<Chambre> retrieveAllChambres(){
       return chabreservice.retrieveAllChambres();
    }


@PostMapping("/ajouterchambre")
 public    Chambre addChambre( @RequestBody  Chambre c){
        Chambre chamb = chabreservice.addChambre(c);
        return  chamb;

}
@PutMapping("/modifierchambre")
  public  Chambre updateChambre ( @RequestBody  Chambre c){
    Chambre chambr = chabreservice.updateChambre(c);
    return chambr;
}

    @GetMapping("/getchambrebyid/{idChambre}")
     public  Chambre retrieveChambre ( @PathVariable("idChambre") long idChambre){
        return chabreservice.retrieveChambre(idChambre);

  }

  @GetMapping("/get-chmbre-bloc/{nomk}")
    public List<Chambre> getchambresByNomBloc(@PathVariable("nomk") String nomk){
       List<Chambre> list =  chabreservice.getChambresParNomBloc(nomk);
       return list;
  }

    @DeleteMapping("/removeChambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chambreId) {
        chabreservice.removeChambre(chambreId);
    }

  @GetMapping("nbChambre/{type}/{idBloc}")
  public long nbChambreParTypeEtBloc(@PathVariable("type") TypeChambre type, @PathVariable("idBloc") long idBloc){
       return chabreservice.nbChambreParTypeEtBloc(type,idBloc);
  }

   /* @GetMapping("/exportChambre")
    public void exportChambreToExcel(HttpServletResponse response) throws IOException {
        byte[] excelData = chabreservice.exportChambreDataToExcel();

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=chambre-data.xlsx");
        response.setContentLength(excelData.length);
        response.getOutputStream().write(excelData);
        response.getOutputStream().flush();
    }*/

    @GetMapping("/sortedByType")
    public List<Chambre> getChambresSortedByType() {
        List<Chambre> chambres = chabreservice.retrieveAllChambres();
        chambres.sort(Comparator.comparing(chambre -> chambre.getTypeC()));
        return chambres;
    }

    @GetMapping("/sortedByBloc")
    public List<Chambre> getChambresSortedByBloc() {
        List<Chambre> chambres = chabreservice.retrieveAllChambres();
        chambres.sort(Comparator.comparing(chambre -> chambre.getBloc().getNomBloc()));
        return chambres;
    }

    @GetMapping("nombreDisp")
    public Map<String, Long> nbrDisponible() {
        Map<String, Long> cc = chabreservice.nbPlacesDisponibleParChambreAnneeEnCours();
        return cc;
    }
}
