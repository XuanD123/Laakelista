package backend.laakelista.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PotilaanLaakeRepository extends CrudRepository<PotilaanLaake, Long> {
    List<PotilaanLaake> findByPotilasId(Long potilasId); // hae lääkkeet tietylle potilaalle
    
}

