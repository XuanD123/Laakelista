package backend.laakelista.web;


import backend.laakelista.domain.Laake;
import backend.laakelista.domain.LaakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/laakkeet")
public class LaakeRestController {

    @Autowired
    private LaakeRepository laakeRepository;

    // Palauta kaikki lääkkeet JSONina
    @GetMapping
    public Iterable<Laake> getAllLaakkeet() {
        return laakeRepository.findAll();
    }

    // Palauta lääke id:n mukaan JSONina
     @GetMapping("/{id}")
    public Optional<Laake> getLaakeById(@PathVariable Long id) {
    return laakeRepository.findById(id);
    }
}
