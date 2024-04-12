package tn.esprit.twin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemottApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemottApplication.class, args);
    }

}
