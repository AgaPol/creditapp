package pl.calculator.creditapp;

import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
@Entity
public class MalejacaRata extends Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double rata; // wysokość raty

    public MalejacaRata(){
    }

    public MalejacaRata(int l, int y, double p) {
        super(l, y, p);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRata() {
        return rata;
    }

    public void setRata(double r) {
        this.rata = r;
    }

    @Override
    public String toString() {
        return "" + rata;
    }
}
