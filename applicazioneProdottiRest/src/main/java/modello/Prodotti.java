package modello;

import javax.persistence.*;
import java.util.Date;

@Entity
    public class Prodotti {
        @Id
        @GeneratedValue
        private Long id;
        private String nome;
        private float prezzo;
        private float ranking;

               @Temporal(TemporalType.DATE)
       private String dataAcquisto;

    public Prodotti(String nome, float prezzo, float ranking, String dataAcquisto) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.ranking = ranking;
        this.dataAcquisto = dataAcquisto;
    }

    public Prodotti(String nome, Date date, float prezzo, String dataAcquisto) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

    public String getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(String dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }
}
