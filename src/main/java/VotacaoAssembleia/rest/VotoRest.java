package VotacaoAssembleia.rest;

import VotacaoAssembleia.acervo.AssociadoRepository;
import VotacaoAssembleia.acervo.PautaRepository;
import VotacaoAssembleia.acervo.VotoRepository;
import VotacaoAssembleia.dominio.Associado;
import VotacaoAssembleia.dominio.Decisao;
import VotacaoAssembleia.dominio.Pauta;
import VotacaoAssembleia.dominio.Voto;
import VotacaoAssembleia.gerenciador.VotoGerenciador;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/voto")


public class VotoRest {

//    private VotoGerenciador gerenciador = new VotoGerenciador();

    @Autowired
    private VotoGerenciador votoGerenciador;

    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @GetMapping
    public ResponseEntity<List<Voto>> findAll(){
        List<Voto> list=votoGerenciador.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Voto> findById(@PathVariable Integer id){
        Voto obj = votoGerenciador.findById(id);
        return ResponseEntity.ok().body(obj);
    }


//    @GetMapping
//    public List<Voto> listarTodosVotos() {
//        return gerenciador.listar();
//    }

//    @PostMapping
////    @PostMapping("/Pauta/{idPauta}/Associado/{idAssociado}/Escolha/{escolha}")
//    public ResponseEntity<Voto> insert(@RequestBody Voto obj){
//        System.out.println(obj);
//        System.out.println("iniciou post mapping voto");
////    public ResponseEntity<Voto> insert(@PathVariable("idPauta") int idPauta, @PathVariable("idAssociado")int idAssociado, @PathVariable("escolha")char escolha){
//        obj = votoGerenciador.insert(obj);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(obj.getIdVoto()).toUri();
//        System.out.println("teste salvar voto");
//        return ResponseEntity.created(uri).body(obj);
//    }




    @PostMapping("/Pauta/{idPauta}/Associado/{idAssociado}/Escolha/{escolha}")
    public Voto cadastrarVoto(@PathVariable("idPauta") int idPauta, @PathVariable("idAssociado")int idAssociado, @PathVariable("escolha") char escolha)  {
        System.out.println("idPauta: "+idPauta);
        System.out.println("idAssociado: "+idAssociado);
        System.out.println("escolha: "+escolha);

        return votoGerenciador.salvar(idPauta,idAssociado,escolha);
    }

//    @PostMapping("/Pauta/{idPauta}/Associado/{idAssociado}/Escolha/{escolha}")
//    public Voto cadastrarVoto(@PathVariable("idPauta") int idPauta, @PathVariable("idAssociado")int idAssociado, @PathVariable("escolha")String escolha)  {
//        System.out.println("idPauta: "+idPauta);
//        System.out.println("idAssociado: "+idAssociado);
//        Voto obj = new Voto(pautaRepository.getOne(idPauta), associadoRepository.getOne(idAssociado), escolha);
//        System.out.println("obj: "+obj);
//        obj = votoGerenciador.insert(obj);
////        return votoGerenciador.findById(1);
////        return null;
//        return obj;
////        return ResponseEntity.ok().body(obj);

//    }
}



