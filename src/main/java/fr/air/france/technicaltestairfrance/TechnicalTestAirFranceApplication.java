package fr.air.france.technicaltestairfrance;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This is the Entry point of the Spring boot application.
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class TechnicalTestAirFranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestAirFranceApplication.class, args);
	}

	/**
	 * This method with @Bean annotation permit to add this bean to spring start up beans.
	 * <p> This is for dependency injection and avoid instantiate many objects.
	 */
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
