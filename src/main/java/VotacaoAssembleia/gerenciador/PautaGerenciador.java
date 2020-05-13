package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.PautaRepository;
import VotacaoAssembleia.dominio.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PautaGerenciador {

    @Autowired
    private PautaRepository pautaRepository;

    public List<Pauta> findAll(){
        return pautaRepository.findAll();
    }

    public Pauta findById(Integer id){
        Optional<Pauta> obj=pautaRepository.findById(id);
        return obj.get();
    }

    public Pauta insert(Pauta obj){
        return pautaRepository.save(obj);
    }



//    public Pauta pesquisar(int id) {
//        if (id > 0) {
//            return acervo.pesquisar(id);
//        }
//        return null;
//    }

//    public boolean deletar(int id) {
//        if (id > 0) {
//            return acervo.deletar(id);
//        }
//        return false;
//    }

//    public Pauta editar(int id, Pauta pautaAtualizado) {
//
//        Pauta pautaParaEditar = pesquisar(id);
//
//        if (pautaParaEditar == null) {
//            return null;
//        } else {
//            return acervo.editar(pautaParaEditar, pautaAtualizado);
//        }
//    }

}

