package backend.laakelista.domain;

import org.springframework.data.repository.CrudRepository;

public interface PotilasRepository extends CrudRepository<Potilas, Long> {
    // Lisää mahdolliset kyselyt täällä, esim. findById
}
