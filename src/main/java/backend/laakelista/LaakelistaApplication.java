package backend.laakelista;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.laakelista.domain.Laake;
import backend.laakelista.domain.LaakeRepository;
import backend.laakelista.domain.PotilaanLaake;
import backend.laakelista.domain.PotilaanLaakeRepository;
import backend.laakelista.domain.Potilas;
import backend.laakelista.domain.PotilasRepository;


@SpringBootApplication
public class LaakelistaApplication {

	/*private final CommandLineRunner demo;

    LaakelistaApplication(CommandLineRunner demo) {
        this.demo = demo;
    }*/

    public static void main(String[] args) {
		SpringApplication.run(LaakelistaApplication.class, args);
	}
/*@Bean
    public CommandLineRunner demo(LaakeRepository repo) {
        return (args) -> {
            repo.save(new Laake("Panadol", "500 mg", "1 tabletti"));
            repo.save(new Laake("Burana", "400 mg", "2 tablettia" ));
            repo.save(new Laake("Amlodipiini", "10 mg", "2 tablettia"));
        };
    }*/

  /*@Bean
    public CommandLineRunner demo(PotilasRepository potilasRepo, PotilaanLaakeRepository laakeRepo) {
        return (args) -> {
            // Lisää potilaita
            Potilas potilas1 = new Potilas("Matti Nieminen");
            Potilas potilas2 = new Potilas("Maija Le");
            potilasRepo.save(potilas1);
            potilasRepo.save(potilas2);

            // Lisää lääkkeitä potilaille
            PotilaanLaake laake1 = new PotilaanLaake("Panadol", "500 mg", "1 tabletti");
            laake1.setPotilas(potilas1); // liitetään potilaaseen
            PotilaanLaake laake2 = new PotilaanLaake("Burana", "400 mg", "2 tablettia");
            laake2.setPotilas(potilas1);

            PotilaanLaake laake3 = new PotilaanLaake("Amlodipiini", "10 mg", "1 tabletti");
            laake3.setPotilas(potilas2);

            laakeRepo.save(laake1);
            laakeRepo.save(laake2);
            laakeRepo.save(laake3);
        };
    }
}*/
@Bean
public CommandLineRunner demo(
        PotilasRepository potilasRepo, 
        LaakeRepository laakeRepo, 
        PotilaanLaakeRepository plRepo) {
    return (args) -> {
        // 1️⃣ Lisää potilaita
        Potilas potilas1 = new Potilas("Matti Nieminen", "Kontulan hoitokoti");
        Potilas potilas2 = new Potilas("Maija Le", "Hertoniemi hoitokoti");
        potilasRepo.save(potilas1);
        potilasRepo.save(potilas2);

        // 2️⃣ Lisää lääkkeitä
        Laake panadol = new Laake("Panadol", "500 mg");
        Laake burana = new Laake("Burana", "400 mg");
        Laake amlodipiini = new Laake("Amlodipiini", "10 mg");
        laakeRepo.save(panadol);
        laakeRepo.save(burana);
        laakeRepo.save(amlodipiini);

        // 3️⃣ Liitä lääkkeet potilaisiin
        PotilaanLaake pl1 = new PotilaanLaake(potilas1, panadol, "1 tabletti");
        PotilaanLaake pl2 = new PotilaanLaake(potilas1, burana, "2 tablettia");
        PotilaanLaake pl3 = new PotilaanLaake(potilas2, amlodipiini, "1 tabletti");

        plRepo.save(pl1);
        plRepo.save(pl2);
        plRepo.save(pl3);
    };
}
}