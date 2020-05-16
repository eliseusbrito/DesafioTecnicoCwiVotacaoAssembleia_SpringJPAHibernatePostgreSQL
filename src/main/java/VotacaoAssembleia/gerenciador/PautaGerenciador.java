package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.PautaRepository;
import VotacaoAssembleia.dominio.Pauta;
import VotacaoAssembleia.gerenciador.exceptions.DatabaseException;
import VotacaoAssembleia.gerenciador.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.controleCadastroExistente;

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

    public Pauta insert(Pauta pauta){
        List<Pauta> pautas = pautaRepository.findAll();
        for (Pauta pautaExistente : pautas) {
            if (pauta.getDescricao().equals(pautaExistente.getDescricao())) {
                System.out.println("Descrição já existente. A pauta não foi cadastrada. ");
                String descricao = pauta.getDescricao();
                controleCadastroExistente = 1;
                return null;
            }
        }
        return pautaRepository.save(pauta);
    }

    public void delete(Integer id) {
        try {
            pautaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Pauta update(Integer id, Pauta obj) {
        try {
            Pauta entity = pautaRepository.getOne(id);
            updateData(entity, obj);
            return pautaRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Pauta entity, Pauta obj) {
        entity.setDescricao(obj.getDescricao());
        entity.setVotacao(obj.getVotacao());
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

