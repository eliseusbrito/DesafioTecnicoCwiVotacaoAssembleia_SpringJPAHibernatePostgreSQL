package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.AssociadoRepository;
import VotacaoAssembleia.dominio.Associado;
import VotacaoAssembleia.gerenciador.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AssociadoGerenciador {

    @Autowired
    private AssociadoRepository associadoRepository;

    public List<Associado> findAll(){
        System.out.println("Listou todos Associados.");
        return associadoRepository.findAll();
        }

    public Associado findById(Integer id){
        Optional<Associado> obj=associadoRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Associado insert(Associado associado){
        System.out.println("Iniciou processo de salvar associado.");
        List<Associado> associados = associadoRepository.findAll();
        if (associado.getNome().equals("")||associado.getCpf().equals("")){
            System.out.println("Algum campo não foi prenchido. O associado não foi cadastrado.");
            return null;        }
        for (Associado associadoExistente : associados) {
            if (associado.getCpf().equals(associadoExistente.getCpf())) {
                System.out.println("CPF já existente. O associado não foi cadastrado. ");
                return associadoExistente;
            }}

        return associadoRepository.save(associado);
    }

}






