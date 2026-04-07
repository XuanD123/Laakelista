package backend.laakelista.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import backend.laakelista.domain.Laake;
import backend.laakelista.domain.LaakeRepository;
import backend.laakelista.domain.PotilaanLaake;
import backend.laakelista.domain.PotilaanLaakeRepository;
import backend.laakelista.domain.Potilas;
import backend.laakelista.domain.PotilasRepository;

@Controller
public class PotilaanLaakeController {
    
    private final PotilaanLaakeRepository potilaanLaakeRepo;
    private final PotilasRepository potilasRepo;
    private final LaakeRepository laakeRepo;

    public PotilaanLaakeController(PotilaanLaakeRepository potilaanLaakeRepo, PotilasRepository potilasRepo, LaakeRepository laakeRepo) {
        this.potilaanLaakeRepo = potilaanLaakeRepo;
        this.potilasRepo = potilasRepo;
        this.laakeRepo = laakeRepo;
    }
        // Näytä tietyn potilaan lääkityslista
    @GetMapping("/potilas/{id}/laakkeet")
    public String potilaanLaakkeet(@PathVariable("id") Long id, Model model) {
        Potilas potilas = potilasRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid potilas Id:" + id));
        model.addAttribute("potilas", potilas);
        model.addAttribute("laakkeet", potilaanLaakeRepo.findByPotilasId(id));
        model.addAttribute("kaikkiLaakkeet", laakeRepo.findAll());
        return "potilaanlaakkeet"; // Thymeleaf template
    }
    //Lisää uusi lääke potilaalle
    @PostMapping("/potilaanlaakkeet/lisaa")
    public String lisaaLaake(@RequestParam Long potilasId,
                             @RequestParam Long laakeId,
                             @RequestParam String annos,
                             RedirectAttributes redirectAttributes) {

        Potilas potilas = potilasRepo.findById(potilasId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid potilas Id:" + potilasId));
        Laake laake = laakeRepo.findById(laakeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid laake Id:" + laakeId));

        PotilaanLaake pl = new PotilaanLaake(potilas, laake, annos);
        potilaanLaakeRepo.save(pl);

        //redirectAttributes.addFlashAttribute("viesti", "Lääke lisätty potilaalle!");
        //return "redirect:/potilaanlaakkeet/potilas/" + potilasId;
        return "redirect:/potilas/" + potilasId + "/laakkeet";
    }
     //Muokkaa potilaan lääkettä
    @PostMapping("/potilaanlaakkeet/muokkaa/{id}")
    public String muokkaaLaake(@PathVariable Long id,
                               @RequestParam String annos,
                               RedirectAttributes redirectAttributes) {
        PotilaanLaake pl = potilaanLaakeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid PotilaanLaake Id:" + id));

        pl.setAnnos(annos);
        potilaanLaakeRepo.save(pl);

        //redirectAttributes.addFlashAttribute("viesti", "Annos päivitetty!");
        //return "redirect:/potilaanlaakkeet/potilas/" + pl.getPotilas().getId();
        return "redirect:/potilas/" + pl.getPotilas().getId() + "/laakkeet";
        
    }

    //Poista potilaan lääke
    @GetMapping("/potilaanlaakkeet/poista/{id}")
    public String poistaLaake(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        PotilaanLaake pl = potilaanLaakeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid PotilaanLaake Id:" + id));

        Long potilasId = pl.getPotilas().getId();
        potilaanLaakeRepo.delete(pl);

        //redirectAttributes.addFlashAttribute("viesti", "Lääke poistettu!");
        //return "redirect:/potilaanlaakkeet/potilas/" + potilasId;
        return "redirect:/potilas/" + potilasId + "/laakkeet";
    }
}

 