package backend.laakelista.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Potilas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nimi;
    private String osoite;


    // Yksi potilas - monta lääkettä (OneToMany)
    @OneToMany(mappedBy = "potilas", cascade = CascadeType.ALL)
    private List<PotilaanLaake> laakkeet;

    public Potilas() {}

    public Potilas(String nimi, String osoite) {
        this.nimi = nimi;
        this.osoite = osoite;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }

    public String getOsoite() { return osoite; }
    public void setOsoite(String osoite) { this.osoite = osoite; }

    public List<PotilaanLaake> getLaakkeet() { return laakkeet; }
    public void setLaakkeet(List<PotilaanLaake> laakkeet) { this.laakkeet = laakkeet; }
}