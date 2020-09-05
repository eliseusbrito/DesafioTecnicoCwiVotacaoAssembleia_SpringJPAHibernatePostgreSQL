package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.AssociadoRepository;
import VotacaoAssembleia.dominio.Associado;
import VotacaoAssembleia.gerenciador.exceptions.DatabaseException;
import VotacaoAssembleia.gerenciador.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.*;

@Service
public class AssociadoGerenciador {

    @Autowired
    private AssociadoRepository associadoRepository;

    public List<Associado> findAll() {
        System.out.println("Listou todos Associados.");
        return associadoRepository.findAll();
    }

    public Associado findById(Integer id) {
        Optional<Associado> obj = associadoRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Associado findByCpf(String cpf) {
        List<Associado> associados = associadoRepository.findAll();
        for (Associado associadoExistente : associados) {
            if (cpf.equals(associadoExistente.getCpf())) {
                System.out.println("CPF existente.");
                int id = associadoExistente.getId();
                Associado associado = findById(id);
                System.out.println(associado);
                return associado;
            }
        }
        return null;
    }

    public Associado insert(Associado associado) {
        if (associado.getNome().equals("") || associado.getCpf().equals("")) {
            System.out.println("Algum campo não foi prenchido. O associado não foi cadastrado.");
            return null;
        }
        Integer quantidadeDeAssociadosComMesmoCpf = associadoRepository.countByCpf(associado.getCpf());
        if (quantidadeDeAssociadosComMesmoCpf>0){
            System.out.println("CPF já existente. O associado não foi cadastrado. ");
            return null;
        }
        return associadoRepository.save(associado);
    }

    public void delete(Integer id) {
        try {
            associadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Associado update(Integer id, Associado obj) {
        try {
            Associado entity = associadoRepository.getOne(id);
            updateData(entity, obj);
            return associadoRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Associado entity, Associado obj) {
        entity.setNome(obj.getNome());
        entity.setCpf(obj.getCpf());
    }

}






