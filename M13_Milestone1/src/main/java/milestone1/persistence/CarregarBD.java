package milestone1.persistence;

import static milestone1.domain.Empleat.Feines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import milestone1.domain.Empleat;

@Configuration
public class CarregarBD {
	
	private static final Logger log = LoggerFactory.getLogger(CarregarBD.class);
	
	@Bean
	CommandLineRunner initBD(EmpleatRepositori repositori) {
		return args -> {
			repositori.save(new Empleat("Eduard", "Lara", Feines.CAPITA));
			repositori.save(new Empleat("Lara", "S.", Feines.OFICIAL));
			repositori.save(new Empleat("Andreu", "Vinyoles", Feines.MARINER));
			repositori.save(new Empleat("Gerard", "Puig", Feines.MARINER));
			repositori.save(new Empleat("Miguel", "Blasco", Feines.MARINER));
			repositori.save(new Empleat("Roger", "Torrent", Feines.POLISSO));
			
			repositori.findAll().forEach(empleat -> log.info("Carregant " + empleat + "..."));
		};
	}
	
}
