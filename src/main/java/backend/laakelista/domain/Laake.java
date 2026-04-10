package backend.laakelista.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Laake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nimi;
    private String vahvuus;

    // Lista PotilaanLaake-suhteita
    @OneToMany(mappedBy = "laake", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore //potilaanLaakkeet jätetään pois JSON-vastauksesta
    private Set<PotilaanLaake> potilaanLaakkeet = new HashSet<>();

    public Laake() {}

    public Laake(String nimi, String vahvuus) {
        this.nimi = nimi;
        this.vahvuus = vahvuus;
    }

    // Getterit ja setterit
    public Long getId() { return id; }

    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }

    public String getVahvuus() { return vahvuus; }
    public void setVahvuus(String vahvuus) { this.vahvuus = vahvuus; }

    public Set<PotilaanLaake> getPotilaanLaakkeet() { return potilaanLaakkeet; }
    public void setPotilaanLaakkeet(Set<PotilaanLaake> potilaanLaakkeet) { this.potilaanLaakkeet = potilaanLaakkeet; }
}