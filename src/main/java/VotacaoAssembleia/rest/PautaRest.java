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
//    @GetMapping
//    public List<Pauta> listarTodasPautas() {
//        return gerenciador.listar();
//    }
//
//    @GetMapping("{id}")
//    public Pauta buscarPorId(@PathVariable("id") int id) {
//        return gerenciador.pesquisar(id);
//    }

    @PostMapping
    public ResponseEntity<Pauta> insert(@RequestBody @Valid Pauta obj){
        obj = pautaGerenciador.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdPauta()).toUri();
        System.out.println("Teste salvar Pauta");
        return ResponseEntity.created(uri).body(obj);
    }

//    @PostMapping
//    public Pauta cadastrar(@RequestBody Pauta pauta) {
//        return gerenciador.salvar(pauta);
//    }

}
