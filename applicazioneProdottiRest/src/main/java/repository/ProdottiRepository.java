package repository;

import modello.Prodotti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProdottiRepository extends JpaRepository<Prodotti, Long> {


               List<Prodotti> findByNome(String nome);

               List<Prodotti> findByDataAcquistoBetween(Date datada, Date dataa);

               List<Prodotti> findByRankingBetween(Double minimo,Double massimo );

               List<Prodotti> findByRankingLessThan(Double minimo);

               List<Prodotti> findByPrezzoBetween(float minimo, float massimo);
}
