package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.AssociadoRepository;
import VotacaoAssembleia.dominio.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AssociadoGerenciador {

    @Autowired
    private AssociadoRepository associadoRepository;

    public List<Associado> findAll(){
        return associadoRepository.findAll();
        }

    public Associado findById(Integer id){
        Optional<Associado> obj=associadoRepository.findById(id);
        return obj.get();
    }

    public Associado insert(Associado associado){
        List<Associado> associados = associadoRepository.findAll();
        if (associado.getNome().equals("")||associado.getCpf().equals("")){
            System.out.println("Algum campo não foi prenchido. O associado não foi cadastrado.");
            return null;        }
        for (Associado associadoExistente : associados) {
            if (associado.getCpf().equals(associadoExistente.getCpf())) {
                System.out.println("CPF já existente. O associado não foi cadastrado. ");
                return associadoExistente;
            }}
//        List<Associado> results = associadoRepository.findAll();

//        System.out.println(results.size());
//        for (Associado x : results){
//            System.out.println(results.get);
//        }

        return associadoRepository.save(associado);
    }

}






