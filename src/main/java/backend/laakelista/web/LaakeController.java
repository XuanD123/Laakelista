package backend.laakelista.web;

import backend.laakelista.domain.Laake;
import backend.laakelista.domain.LaakeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/laake")
public class LaakeController {

    private final LaakeRepository laakeRepo;

    public LaakeController(LaakeRepository laakeRepo) {
        this.laakeRepo = laakeRepo;
    }

    // Lista kaikki lääkkeet
    @GetMapping
    public String lista(Model model) { 
        model.addAttribute("laakkeet", laakeRepo.findAll());
        return "laake"; // laakkeet.html
    }

    // Lomake uuden lääkkeen lisäämiseksi
    @GetMapping("/lisaalaake")
    public String uusiLaakeForm(Model model) {
        model.addAttribute("laake", new Laake());
        return "lisaalaake"; // uusiLaakeAdmin.html
    }

    // Tallenna uusi lääke
    @PostMapping("/lisaalaake")
    public String tallennaLaake(@ModelAttribute Laake laake) {
        laakeRepo.save(laake);
        return "redirect:/laake";
    }

    // Poista lääke
    @GetMapping("/poista/{id}")
    public String poistaLaake(@PathVariable Long id) {
        laakeRepo.deleteById(id);
        return "redirect:/laake";
    }

    @PostMapping("/muokkaa/{id}")
    public String tallennaMuokattuLaake(@PathVariable Long id, @ModelAttribute Laake laake) {
        Laake olemassa = laakeRepo.findById(id).orElseThrow();
        olemassa.setNimi(laake.getNimi());
        olemassa.setVahvuus(laake.getVahvuus());
        laakeRepo.save(olemassa);
        return "redirect:/laake";
    }
}