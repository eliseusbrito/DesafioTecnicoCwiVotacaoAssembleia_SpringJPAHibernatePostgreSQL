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
    public ResponseEntity<Associado> insert(@RequestBody @Valid Associado obj){
        obj = associadoGerenciador.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

}
