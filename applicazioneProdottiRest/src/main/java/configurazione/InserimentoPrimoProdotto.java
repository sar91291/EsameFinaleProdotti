package configurazione;

import modello.Prodotti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.ProdottiRepository;

import java.util.Date;

@Configuration
       public class InserimentoPrimoProdotto {
           private static final Logger log = LoggerFactory.getLogger(InserimentoPrimoProdotto.class);
          @Bean
          CommandLineRunner initDatabase(ProdottiRepository repository) {
                      return args -> {
                              log.info("Preloading " + repository.save(new Prodotti("Acquario",12, 5.1f,"01/01/1999")));
                               log.info("Preloading " + repository.save(

                                               new Prodotti("Scopa",new Date(), 1.5F,"01/01/1999" )));
                              log.info("Preloading " + repository.save(new Prodotti("Lampada", new Date(), 2.4f, "01/01/1999")));
                          };
                 }

             }
