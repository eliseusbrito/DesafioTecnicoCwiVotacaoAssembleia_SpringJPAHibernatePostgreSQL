package VotacaoAssembleia.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_pauta")
public class Pauta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPauta;
    @NotBlank
    private String descricao;

    @OneToOne(mappedBy = "pauta",cascade = CascadeType.ALL )
    private Votacao votacao;

    @JsonIgnore
    @OneToMany(mappedBy = "pauta")
    private List<Voto> votos = new ArrayList<>();

    public Pauta() {
    }

    public Pauta(@NotBlank String descricao,  Votacao votacao) {
        this.descricao = descricao;
        this.votacao = votacao;
    }

    public Pauta(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    public int getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(int idPauta) {
        this.idPauta = idPauta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public void setVotacao(Votacao votacao) {
        this.votacao = votacao;
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "idPauta=" + idPauta +
                ", descricao='" + descricao + '\'' +
                ", votacao=" + votacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pauta)) return false;
        Pauta pauta = (Pauta) o;
        return getIdPauta() == pauta.getIdPauta();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPauta());
    }
}
