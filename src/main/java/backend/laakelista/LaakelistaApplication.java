package backend.laakelista;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.laakelista.domain.AppUser;
import backend.laakelista.domain.AppUserRepository;
import backend.laakelista.domain.Laake;
import backend.laakelista.domain.LaakeRepository;
import backend.laakelista.domain.PotilaanLaake;
import backend.laakelista.domain.PotilaanLaakeRepository;
import backend.laakelista.domain.Potilas;
import backend.laakelista.domain.PotilasRepository;


@SpringBootApplication
public class LaakelistaApplication {

    private static final Logger log = LoggerFactory.getLogger(LaakelistaApplication.class);


    public static void main(String[] args) {
		SpringApplication.run(LaakelistaApplication.class, args);
	}

	
@Bean
public CommandLineRunner demo(PotilasRepository potilasRepo, LaakeRepository laakeRepo, 
        PotilaanLaakeRepository plRepo, AppUserRepository appUserRepository) {
    return (args) -> {
        log.info("save a couple of patients ");
        // 1️⃣ Lisää potilaita
        Potilas potilas1 = new Potilas("Matti Nieminen", "Kontulan hoitokoti");
        Potilas potilas2 = new Potilas("Maija Le", "Hertoniemi hoitokoti");
        potilasRepo.save(potilas1);
        potilasRepo.save(potilas2);

        log.info("save a couple of medicines");
        // 2️⃣ Lisää lääkkeitä
        Laake panadol = new Laake("Panadol", "500 mg");
        Laake burana = new Laake("Burana", "400 mg");
        Laake amlodipiini = new Laake("Amlodipiini", "10 mg");
        laakeRepo.save(panadol);
        laakeRepo.save(burana);
        laakeRepo.save(amlodipiini);

        log.info("save a couple of patients medicines ");
        // 3️⃣ Liitä lääkkeet potilaisiin
        PotilaanLaake pl1 = new PotilaanLaake(potilas1, panadol, "1 tabletti");
        PotilaanLaake pl2 = new PotilaanLaake(potilas1, burana, "2 tablettia");
        PotilaanLaake pl3 = new PotilaanLaake(potilas2, amlodipiini, "1 tabletti");

        plRepo.save(pl1);
        plRepo.save(pl2);
        plRepo.save(pl3);

        // Create users: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			appUserRepository.save(user1);
			appUserRepository.save(user2);

            log.info("fetch all potilaat");
			for (Potilas potilas: potilasRepo.findAll()) {
				log.info(potilas.toString());
            }
			
			log.info("fetch all laake");
			for (Laake laake : laakeRepo.findAll()) {
				log.info(laake.toString());
            }
            
            log.info("fetch all potilaanlaake");
			for (PotilaanLaake potilaanlaake : plRepo.findAll()) {
				log.info(potilaanlaake.toString());
			}
        };
    }
}
