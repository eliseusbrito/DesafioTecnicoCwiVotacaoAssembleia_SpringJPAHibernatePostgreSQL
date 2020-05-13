package VotacaoAssembleia.acervo;

import VotacaoAssembleia.dominio.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotacaoRepository extends JpaRepository<Votacao, Integer> {

}
