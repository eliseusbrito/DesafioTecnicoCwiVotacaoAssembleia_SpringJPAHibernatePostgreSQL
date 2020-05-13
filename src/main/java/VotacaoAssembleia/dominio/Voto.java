package VotacaoAssembleia.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_voto")
public class Voto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVoto;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    private char escolha;

    public Voto() {
    }

    public Voto(Pauta pauta, Associado associado, char escolha) {
        this.pauta = pauta;
        this.associado = associado;
        this.escolha = escolha;
    }

    public int getIdVoto() {
        return idVoto;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public void setEscolha(char escolha) {
        this.escolha = escolha;
    }

    public char getEscolha() {
        return escolha;
    }

    @Override
    public String toString() {
        return "Voto{" +
                "idVoto=" + idVoto +
                ", pauta=" + pauta +
                ", associado=" + associado +
                ", escolha='" + escolha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voto)) return false;
        Voto voto = (Voto) o;
        return getIdVoto() == voto.getIdVoto();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVoto());
    }


}
