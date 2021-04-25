package pl.calculator.creditapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MalejacaRataService extends MalejacaRata implements Rata{

    @Autowired
    private MalejacaRepo malejacaRepo;
    @Autowired
    private MalejacaRata malejacaR;

    double rk = 0; //rata kapitałowa
    double ro = 0; //rata odestkowa


    public MalejacaRataService(int l, int y, double p, MalejacaRepo malejacaRepo) {
        super(l, y, p);
        this.malejacaRepo = malejacaRepo;
        malejacaRepo.deleteAll();
        rk = l/n;
    }

    @Override
    public double calculateRata(int l, double p){

        for (int i = 0; i<n ; i++) {
            ro = ((l - i * rk) * p * 0.01) / 12;
            this.malejacaR = new MalejacaRata();
            malejacaR.setRata(rk+ro);
            malejacaRepo.save(malejacaR);
        }
        return ro;
    }

        @Override
        public double calculateCalkowitaKwotaKredytu(){ return n;
        }

        @Override
        public double calculateOdsetki(int l){
            return n*ro-l;
        }

}
