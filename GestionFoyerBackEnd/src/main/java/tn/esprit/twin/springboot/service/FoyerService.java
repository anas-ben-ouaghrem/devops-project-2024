package tn.esprit.twin.springboot.service;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot.entity.Bloc;
import tn.esprit.twin.springboot.entity.Foyer;
import tn.esprit.twin.springboot.repository.BlocRepository;
import tn.esprit.twin.springboot.repository.FoyerRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FoyerService implements  IFoyerService{

    FoyerRepository foyerRepository;
    BlocRepository br;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    ;

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    ;

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(Long idFoyer) {

        return foyerRepository.findById(idFoyer).get();

    }

    @Override
    public  Long capacitemaximale(long idfoyer) {
        Foyer foyer = foyerRepository.findById(idfoyer).get();
        List<Bloc> b = br.findByFoyer_IdFoyer(idfoyer);
        long countBloc = b.stream().count();

        if (foyer.getCapacityFoyer() > countBloc) {

            return ( foyer.getCapacityFoyer() - countBloc);
        } else
            return null;

    }

    @Override
    public void archiverFoyer(long idFoyer) {
        Foyer ff = retrieveFoyer(idFoyer);
        ff.setArchived(true);
        foyerRepository.save(ff);
    }


    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);

    }
    public byte[] exportFoyerToPdf(Foyer foyer) {
        ByteArrayOutputStream biais = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);

                contentStream.newLineAtOffset(50, PDRectangle.A4.getHeight() - 50);
                contentStream.showText("les informations sur le foyer");

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(0, -40);

                contentStream.showText("Nom du foyer: " + foyer.getNomFoyer());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Capacite du foyer: " + foyer.getCapacityFoyer());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Archived: " + foyer.isArchived());

                for (Bloc bloc : foyer.getBloc()) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Nom bloc: " + bloc.getNomBloc());

                }

                contentStream.endText();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            document.save(biais);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return biais.toByteArray();
    }

}
