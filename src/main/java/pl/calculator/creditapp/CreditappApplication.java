package pl.calculator.creditapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class CreditappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditappApplication.class, args);
	}

}
