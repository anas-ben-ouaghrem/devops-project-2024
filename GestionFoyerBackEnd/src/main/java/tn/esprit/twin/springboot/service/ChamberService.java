package tn.esprit.twin.springboot.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot.entity.*;
import tn.esprit.twin.springboot.repository.BlocRepository;
import tn.esprit.twin.springboot.repository.ChambreRepository;
import tn.esprit.twin.springboot.repository.FoyerRepository;
import tn.esprit.twin.springboot.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@AllArgsConstructor
@Slf4j
public class ChamberService implements  IChamberService {

    ChambreRepository chambreRepositorys;
    BlocRepository br;
    FoyerRepository fr;
    ReservationRepository rs;
    @Override
    public List<Chambre> getChambresParNomBloc(String nomBloc) {
        Bloc b = br.findByNomBloc(nomBloc);
        List<Chambre> ch = b.getChambers();

        return ch;
    }

    @Override
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        Bloc b = br.findById(idBloc).get();
        int i = 0;
        long z = 0;
        List<Chambre> ch = b.getChambers();
        while (ch != null) {
            if (ch.get(i).getTypeC() == type)
                z++;
            i++;
        }
        return z;
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomFoyer, TypeChambre type) {
      //List<Chambre> c = chambreRepository.findAllByReservationsEmptyAAndTypeC(type);
        List<Chambre> chambres = new ArrayList<>();
        int i = 0;
        Foyer f = fr.findByNomFoyer(nomFoyer);
        /*List<Bloc> b = f.getBloc();
        //b.get(0).getChambers() = chambreRepository.findAllByReservationsEmptyAAndTypeC(type);
        while(b.get(i) != null)
        {
            List<Chambre> ct = b.get(i).getChambers();

        }*/
        if (f != null) {
            List<Bloc> b = f.getBloc();

            for (Bloc bloc : b) {
                List<Chambre> ct = b.get(i).getChambers();

                for (Chambre chambre : ct) {
                    if (chambre.getReservations().isEmpty() && chambre.getTypeC().equals(type)) {
                        chambres.add(chambre);

                    }

                }

            }

        }
        return chambres;
    }
    @Override
    @Scheduled(fixedRate = 300000)
    public Map<String, Long> pourcentageChambreParTypeChambre()
    {
        List<Chambre> ch = chambreRepositorys.findAll();
        long nbch =  ch.stream().count();
        long countParTypeSIMPLE = chambreRepositorys.countChambresByTypeC(TypeChambre.SIMPLE);
        long countParTypeDOUBLE = chambreRepositorys.countChambresByTypeC(TypeChambre.DOUBLE);
        long countParTypeTRIPLE = chambreRepositorys.countChambresByTypeC(TypeChambre.TRIPLE);
        Map<String, Long> roomStatisticsSet = new HashMap<>();

        roomStatisticsSet.put("Le nombre total des chambres est :" , nbch);
        roomStatisticsSet.put("Le pourcentage des chambres de type SIMPLE est :" ,(countParTypeSIMPLE*100)/nbch);
        roomStatisticsSet.put("Le pourcentage des chambres de type DOUBLE est :" ,(countParTypeDOUBLE*100)/nbch);
        roomStatisticsSet.put("Le pourcentage des chambres de type TRIPLE est :" ,(countParTypeTRIPLE*100)/nbch);

        return roomStatisticsSet;

    }
    @Override
    @Scheduled(fixedRate = 300000)
    public Map<String, Long> nbPlacesDisponibleParChambreAnneeEnCours()
    {
        List<Chambre> ch = chambreRepositorys.findAll();
        long x =0;

        int currentYear = LocalDate.now().getYear();
        LocalDate instance = LocalDate.now().withYear(currentYear);
        LocalDate dateStart = instance.with(firstDayOfYear());
        LocalDate dateEnd = instance.with(lastDayOfYear());
        Date startDate = java.sql.Date.valueOf(dateStart);
        Date endDate = java.sql.Date.valueOf(dateEnd);
        Map<String, Long > roomplacesSet = new HashMap<>();
        for (Chambre chambre : ch) {
            List<Reservation> availablePlaces = rs.findByChambre_NumeroChambreAndEstValideAndAnneeUniversitaireBetween(chambre.getNumeroChambre(),true, startDate ,endDate );
            int nbplace =availablePlaces.size();
            if(chambre.getTypeC() == TypeChambre.SIMPLE)
                x = 1;
            if(chambre.getTypeC() == TypeChambre.DOUBLE)
                x = 2;
            if(chambre.getTypeC() == TypeChambre.TRIPLE)
                x = 3;
            if((x - nbplace) > 0)
            {
                roomplacesSet.put("Chambre ID: " , chambre.getIdChambre());
                roomplacesSet.put(" Places disponibles: " ,(x - nbplace))  ;
                roomplacesSet.put(" pour l'année en cours.",  (long) currentYear);


            }
            else
                roomplacesSet.put("nombre de place est negative de chambre ",chambre.getIdChambre() );

        }
        return roomplacesSet;
    }

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepositorys.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepositorys.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepositorys.save(c);
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        return chambreRepositorys.findById(idChambre).get();
    }

    @Override
    public void removeChambre(Long idChambre) {
        Chambre chambre = chambreRepositorys.findById(idChambre).get();
        if (chambre != null) {
            Bloc bloc = chambre.getBloc();
            if (bloc != null) {
                chambre.setBloc(null);
                bloc.getChambers().remove(chambre);
                chambreRepositorys.save(chambre);
                br.save(bloc);
            }
        }
        chambreRepositorys.deleteById(idChambre);
    }





  /*  public byte[] exportChambreDataToExcel() {
        List<Chambre> chambreList = chambreRepositorys.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Chambre Data");

            int rowNum = 0;
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Numéro Chambre");
            headerRow.createCell(1).setCellValue("Type Chambre");
            headerRow.createCell(2).setCellValue("Nom Bloc");

            for (Chambre chambre : chambreList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(chambre.getNumeroChambre());
                row.createCell(1).setCellValue(chambre.getType().toString());
                row.createCell(2).setCellValue(chambre.getBlocch().getNomBloc());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }*/



}
