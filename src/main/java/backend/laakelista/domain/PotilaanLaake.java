package backend.laakelista.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PotilaanLaake {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "potilas_id")
    private Potilas potilas;

    @ManyToOne
    @JoinColumn(name = "laake_id")
    private Laake laake;
    private String annos;

    // getter & setter
    public Potilas getPotilas() { return potilas; }
    public void setPotilas(Potilas potilas) { this.potilas = potilas; }

    // Parametriton konstruktori JPA:lle
    public PotilaanLaake() {}
    
     public PotilaanLaake(Potilas potilas, Laake laake, String annos) {
        this.potilas = potilas;
        this.laake = laake;
        this.annos = annos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnos() {
        return annos;
    }
    public void setAnnos(String annos) {
        this.annos = annos;
    }
    public Laake getLaake() {
        return laake;
    }
    public void setLaake(Laake laake) {
        this.laake = laake;
    }
    
}
