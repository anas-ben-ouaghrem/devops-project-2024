package tn.esprit.twin.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.twin.springboot.service.BlocService;
import tn.esprit.twin.springboot.service.UniversiteService;

@RestController
public class UniversiteController {
    @Autowired
    UniversiteService U;

}
