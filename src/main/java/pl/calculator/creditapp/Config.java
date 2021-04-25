package pl.calculator.creditapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pl.calculator.creditapp")
public class Config {

    @Autowired
    MalejacaRepo malejacaRepo;

    @Bean
    public StalaRata stalaRata(){
        return new StalaRata(250000, 25, 2.5);
    }

    @Bean
    public MalejacaRata malejacaRata() { return new MalejacaRata(250000, 25, 2.5);}

    @Bean
    public MalejacaRataService malejacaRataService(){
        return new MalejacaRataService(250000, 25, 2.5, malejacaRepo);
    }

    
}
