package VotacaoAssembleia.rest;

import VotacaoAssembleia.dominio.Associado;
import VotacaoAssembleia.dominio.Pauta;
import VotacaoAssembleia.gerenciador.PautaGerenciador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.controleCadastroExistente;

@RestController
@RequestMapping("/pauta")

public class PautaRest {

    private PautaGerenciador gerenciador = new PautaGerenciador();

    @Autowired
    private PautaGerenciador pautaGerenciador;

    @GetMapping
    public ResponseEntity<List<Pauta>> findAll(){
        List<Pauta> list=pautaGerenciador.findAll();
        return  ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pauta> findById(@PathVariable Integer id){
        Pauta obj = pautaGerenciador.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid Pauta pauta){
        pauta = pautaGerenciador.insert(pauta);
        if(controleCadastroExistente==1){
            controleCadastroExistente=0;
            return ResponseEntity.badRequest().body("Pauta já existente. A pauta não foi cadastrada.");
        };
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pauta.getIdPauta()).toUri();
        System.out.println("Teste salvar Pauta");
        return ResponseEntity.created(uri).body(pauta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pautaGerenciador.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pauta> update(@PathVariable Integer id, @RequestBody Pauta obj) {
        obj = pautaGerenciador.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
