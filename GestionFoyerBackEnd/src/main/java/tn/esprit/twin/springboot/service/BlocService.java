package tn.esprit.twin.springboot.service;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Chambre;
import tn.esprit.twin.springboot.entity.Foyer;
import tn.esprit.twin.springboot.repository.BlocRepository;
import tn.esprit.twin.springboot.repository.ChambreRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class BlocService implements  IBlocService{
    private static Logger log = LoggerFactory.getLogger(BlocService.class);
    ChambreRepository chr;
    BlocRepository blocrepository;
    IFoyerService fs;
    public Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc)
    {
        List<Chambre> ll = null ;
        int i = 0;
        Bloc b = blocrepository.findByNomBloc(nomBloc);
        while(numChambre != null)
        {
            Chambre cc = chr.findByNumeroChambre(numChambre.get(i));
            cc.setBloc(b);
            i++;
            chr.save(cc);
        }
        return b;

    }
    public Foyer addFoyerWithBloc(Foyer f)
    {
        fs.addFoyer(f);
        return f;
    }

    //@Scheduled(fixedRate = 10000)
    public void listeChambresParBloc(){

        List<Bloc>blocs = blocrepository.findAll();
        log.info("Size:" +blocs.size());
        for (int i=0;i<blocs.size();i++)
        {
            System.out.println("La capacite du bloc" + blocs.get(i).getNomBloc()+"est :" +blocs.get(i).getCapacityBloc());
            List<Chambre> chambres=blocs.get(i).getChambers();
            chambres.stream().forEach(
                    Chambre ->{
                        log.info("Le numero de la chambre est :" + Chambre.getNumeroChambre()+"et de type :"+Chambre.getTypeC());
                    }
            );
        }
    }

    @Override
    public List<Bloc> retrieveBlocs() {
        return  blocrepository.findAll();
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocrepository.save(bloc);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocrepository.save(bloc);
    }

    @Override
    public void removeBloc(Long idBloc) {
        blocrepository.deleteById(idBloc);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocrepository.findById(idBloc).get();
    }

    @Override
    public void archiverBloc(long idBloc) {
        blocrepository.getReferenceById(idBloc).setArchived(true);
    }




    public long getNumChambreAffected(long blocId) {
        Bloc bloc = blocrepository.findById(blocId).orElse(null);
        long affectedRooms = 0;

        if (bloc != null && bloc.getChambers() != null) {
            for (Chambre chambre : bloc.getChambers()) {
                if (chambre.getBloc() != null) {
                    affectedRooms++;
                }
            }
        }
        return affectedRooms;
    }

    public byte[] exportBlocToPdf(Bloc bloc) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);

                contentStream.newLineAtOffset(50, PDRectangle.A4.getHeight() - 50);
                contentStream.showText("Bloc Information");

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(0, -40);

                contentStream.showText("Nom Bloc: " + bloc.getNomBloc());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Capacite Bloc: " + bloc.getCapacityBloc());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Archived: " + bloc.isArchived());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Chambres:");

                for (Chambre chambre : bloc.getChambers()) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Numéro Chambre: " + chambre.getNumeroChambre());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Type: " + chambre.getTypeC());
                }

                contentStream.endText();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            document.save(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

}
