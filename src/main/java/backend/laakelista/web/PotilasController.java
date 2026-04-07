package backend.laakelista.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import backend.laakelista.domain.PotilasRepository;
import backend.laakelista.domain.PotilaanLaakeRepository;
import backend.laakelista.domain.Potilas;


@Controller
public class PotilasController {

    private final PotilasRepository potilasRepo;
    private final PotilaanLaakeRepository laakeRepo;

    public PotilasController(PotilasRepository potilasRepo, PotilaanLaakeRepository laakeRepo) {
        this.potilasRepo = potilasRepo;
        this.laakeRepo = laakeRepo;
    }

    // Näytä kaikki potilaat
    @GetMapping("/potilaat")
    public String listPotilaat(Model model) {
        model.addAttribute("potilaat", potilasRepo.findAll());
        return "potilaat"; // Thymeleaf template: potilaat.html
    
    }
    //Lisää Potilas
    @GetMapping("/lisaapotilas")
    public String lisaaPotilaat(Model model) {
    model.addAttribute("potilas", new Potilas());
    return "lisaapotilas";
    }
    //talenna potilas
    @PostMapping("/potilas/tallenna")
    public String tallennaPotilas(Potilas potilas) {
    potilasRepo.save(potilas);
    return "redirect:/potilaat";
    }
    // Näytä muokkauslomake
    @GetMapping("/potilas/muokkaa/{id}")
    public String muokkaaPotilas(@PathVariable Long id, Model model) {
    Potilas potilas = potilasRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid potilas Id:" + id));
    model.addAttribute("potilas", potilas);
    return "muokkaapotilas"; // Thymeleaf template
    }

    // Tallenna muutokset
    @PostMapping("/potilas/muokkaa")
    public String tallennaMuokkaus(Potilas potilas) {
    potilasRepo.save(potilas);
    return "redirect:/potilaat";
    }
     
    //poitaa potilas
    @GetMapping("/potilas/poista/{id}")
    public String poistaPotilas(@PathVariable Long id) {
    potilasRepo.deleteById(id);
    return "redirect:/potilaat";
    }

}
