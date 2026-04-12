package backend.laakelista.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LaakeRepository extends CrudRepository<Laake, Long> {
      List<Laake> findByNimi(String nimi);
}

  