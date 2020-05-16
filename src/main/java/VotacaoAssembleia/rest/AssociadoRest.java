package VotacaoAssembleia.rest;

import VotacaoAssembleia.dominio.Associado;
import VotacaoAssembleia.gerenciador.AssociadoGerenciador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.*;

@RestController
@RequestMapping("/associado")

public class AssociadoRest {

    @Autowired
    private AssociadoGerenciador associadoGerenciador;

    @GetMapping
    public ResponseEntity<List<Associado>> findAll(){
        List<Associado> list=associadoGerenciador.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Associado> findById(@PathVariable Integer id){
        Associado obj = associadoGerenciador.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid Associado obj){
        obj = associadoGerenciador.insert(obj);
        if(controleCadastroExistente==1){
            controleCadastroExistente=0;
            return ResponseEntity.badRequest().body("CPF já existente. O associado não foi cadastrado.");
        };
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        associadoGerenciador.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Associado> update(@PathVariable Integer id, @RequestBody Associado obj) {
        obj = associadoGerenciador.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
