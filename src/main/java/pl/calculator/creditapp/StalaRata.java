package pl.calculator.creditapp;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StalaRata extends Credit implements Rata {

    private double r; // wysokość raty
    private double q; //paramter do wyliczenia raty

    public StalaRata(int l, int y, double p) {
        super(l, y, p);

    }

    @Override
    public double calculateRata(int l, double p){
        q = 1+(p*0.01/12);
        r = l*(Math.pow(q,n)*(q-1)/(Math.pow(q,n)-1));
        return r;
    }

    @Override
    public double calculateCalkowitaKwotaKredytu(){
        return n*r;
    }

    @Override
    public double calculateOdsetki(int l){
        return n*r-l;
    }


}
