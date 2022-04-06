package avvisoRest;


import avviso.ProdottoNonTrovato;
import modello.Prodotti;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repository.ProdottiRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;

@RestController
   public class ProdottoRestController {
       private ProdottiRepository repository;

               ProdottoRestController(ProdottiRepository repository) {
                   this.repository = repository;
               }
       @GetMapping("/prodotti")
       List<Prodotti> tutti() {
                 return repository.findAll();
               }
       @PostMapping("/prodotto")
       Prodotti nuovoProdotto(@RequestBody Prodotti nuovoProdotto) {
                 return repository.save(nuovoProdotto);
              }
      @GetMapping("/prodotti/{id}")
       Object singoloProdotto(@PathVariable Long id) {
                  return repository.findById(id)
                          .orElseThrow(() -> new ProdottoNonTrovato(id));
              }
       @PutMapping("/prodotti/{id}")
       Prodotti aggiornaProdotto(@RequestBody Prodotti prodotto, @PathVariable Long id) {
                   return repository.findById(id)
                           .map(products -> {
                                   products.setNome(prodotto.getNome());
                                   products.setRanking(prodotto.getRanking());
                                  return repository.save(products);
                               })
                         .orElseGet(() -> {
                                  prodotto.setId(id);
                                 return repository.save(prodotto);
                               });
            }
      void eliminaProdotto(@PathVariable Long id) {
                 repository.deleteById(id);
             }

              @GetMapping("/prodotto/ricerca/nome/{nome}")

      List<Prodotti> cercaPerNome(@PathVariable String nome) {
                  logger.info("cerco per nome") ;
          //return repository.cercaPerNome(nome);
                   return repository.findByNome(nome);
               }

    @RequestMapping(path = "/prodotto/ricerca/dataAcquisto",
            method = RequestMethod.GET)
    public List<Prodotti> trovaProdottoPerDataDiAcquisto( @DateTimeFormat(pattern = "dd-MM-yyyy") Date datada,
                                                          @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataa)
           {
            logger.info("cerco per data");
                   return repository.findByDataAcquistoBetween(datada, dataa);

        }

    @RequestMapping(path = "/prodotto/ricerca/prezzo",

            method = RequestMethod.GET)
    public List<Prodotti> trovaProdottoPerPrezzo(
            @RequestParam(name="minimo") float minimo,
            @RequestParam(name="massimo") float massimo){
            logger.info("cerco per Prezzo");
        return repository.findByPrezzoBetween(minimo, massimo);
    }
       @RequestMapping(path = "/prodotto/ricerca/ranking",
           
                           method = RequestMethod.GET)
      public List<Prodotti> trovaProdottoPerRanking(
              @RequestParam(name="minimo")
                       Double minimo,
              @RequestParam(name="massimo") Double massimo){
                   logger.info("cerco per Ranking");
                   return repository. findByRankingBetween(minimo, massimo);
              }
       @RequestMapping(path = "/prodotto/ricerca/ranking/minimo",
            
                          method = RequestMethod.GET)
      public List<Prodotti> trovaProdottiPerRanking(
              @RequestParam(name="minimo")
                       Double minimo){
      
                  //return repository.cercaPerRanking(minimo, massimo);
                   return repository.findByRankingLessThan(minimo);
        
             }
       @PostMapping("/prodotti/csv")
       public ResponseEntity<String> caricaCSV(@RequestParam("fileProdotti") MultipartFile file) {
                   Reader in = null;
                  try {
                           in = new InputStreamReader(file.getInputStream());
              // Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
                          Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().build().parse(in);
                          for (CSVRecord record : records) {
                                  String prodotto = record.get(0);
                                  logger.info("Prodotto: " + prodotto);
                                  String prezzo = record.get(1);
                               logger.warn("Prezzo: " + prezzo);
                             }
                    } catch (IOException e) {
                        logger.error("Si è verificato un errore", e);
                    }
                  return ResponseEntity.ok("CSV");
             }
      static Logger logger = LoggerFactory.getLogger(ProdottoRestController.class);
     //prodottoRestController = Logger logger = LoggerFactory.getLogger(prodottoRestController.class);
          }
