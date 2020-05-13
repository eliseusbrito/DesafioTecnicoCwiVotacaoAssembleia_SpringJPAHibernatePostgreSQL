package VotacaoAssembleia.acervo;

import VotacaoAssembleia.dominio.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Integer> {

}
