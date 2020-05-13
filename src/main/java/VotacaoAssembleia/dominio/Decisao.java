package VotacaoAssembleia.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Decisao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Pauta pauta;
    int sim;
    int nao;
    int total;
    String decisao;

    public Decisao(Pauta pauta, int sim, int nao, int total, String decisao) {
        this.pauta = pauta;
        this.sim = sim;
        this.nao = nao;
        this.total = total;
        this.decisao = decisao;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public int getSim() {
        return sim;
    }

    public int getNao() {
        return nao;
    }

    public int getTotal() {
        return total;
    }

    public String getDecisao() {
        return decisao;
    }

    @Override
    public String toString() {
        return "Decisao{" +
                "pauta=" + pauta +
                ", sim=" + sim +
                ", nao=" + nao +
                ", total=" + total +
                ", decisao='" + decisao + '\'' +
                '}';
    }
}
