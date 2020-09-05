package VotacaoAssembleia.acervo;

import VotacaoAssembleia.dominio.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer> {

    Integer countByCpf(String cpf);

}
