package VotacaoAssembleia.dominio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_votacao")
public class Votacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVotacao;

    @OneToOne
    @MapsId
    private Pauta pauta;

    private int sim;
    private int nao;
    private int total;
    private String decisao;

    public Votacao() {
    }

    public Votacao(Pauta pauta, int sim, int nao, int total, String decisao) {
        this.pauta = pauta;
        this.sim = sim;
        this.nao = nao;
        this.total = total;
        this.decisao = decisao;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdVotacao() {
        return idVotacao;
    }

    public void setIdVotacao(int idVotacao) {
        this.idVotacao = idVotacao;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public int getSim() {
        return sim;
    }

    public void setSim(int sim) {
        this.sim = sim;
    }

    public int getNao() {
        return nao;
    }

    public void setNao(int nao) {
        this.nao = nao;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDecisao() {
        return decisao;
    }

    public void setDecisao(String decisao) {
        this.decisao = decisao;
    }

    @Override
    public String toString() {
        return "Votacao{" +
                "idVotacao=" + idVotacao +
                ", pauta=" + pauta +
                ", sim=" + sim +
                ", nao=" + nao +
                ", total=" + total +
                ", decisao='" + decisao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Votacao)) return false;
        Votacao votacao = (Votacao) o;
        return getIdVotacao() == votacao.getIdVotacao();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVotacao());
    }
}
