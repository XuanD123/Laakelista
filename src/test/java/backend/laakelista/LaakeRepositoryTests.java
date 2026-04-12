package backend.laakelista;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import backend.laakelista.domain.Laake;
import backend.laakelista.domain.LaakeRepository;

@DataJpaTest
public class LaakeRepositoryTests {

    @Autowired
    private LaakeRepository repository;

    @Test  // testataan LaakeRepositoryn findByName()-metodin toimivuutta
    public void findByNimiShouldReturnLaake() {
        List<Laake> laakkeet = repository.findByNimi("Panadol");

        assertThat(laakkeet).hasSize(1);
        assertThat(laakkeet.get(0).getVahvuus()).isEqualTo("500 mg");
    }

    @Test
    public void createNewLaake() {
        Laake laake = new Laake("Jardiance", "20 mg");
        repository.save(laake);
        assertThat(laake.getId()).isNotNull();

    }
}
  

  