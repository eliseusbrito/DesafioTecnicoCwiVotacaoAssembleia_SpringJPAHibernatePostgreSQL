package VotacaoAssembleia.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_associado")
public class Associado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "associado")
    private List<Voto> votos = new ArrayList<>();

    public Associado() {
    }

    public Associado(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    @Override
    public String toString() {
        return "Associado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Associado)) return false;
        Associado associado = (Associado) o;
        return getId() == associado.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
