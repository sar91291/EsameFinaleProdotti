package esercitazioneProdottiRest.applicazioneProdottiRest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ApplicazioneProdottiRestApplication {

	private static Logger logger = LoggerFactory.getLogger(ApplicazioneProdottiRestApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ApplicazioneProdottiRestApplication.class, args);
		logger.info("Inizio prova esame ANDREA TRAVASCIO");
	}

}
