package VotacaoAssembleia.rest;

import VotacaoAssembleia.acervo.AssociadoRepository;
import VotacaoAssembleia.acervo.PautaRepository;
import VotacaoAssembleia.acervo.VotoRepository;
import VotacaoAssembleia.dominio.Voto;
import VotacaoAssembleia.gerenciador.VotoGerenciador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.*;

@RestController
@RequestMapping("/voto")

public class VotoRest {

    @Autowired
    private VotoGerenciador votoGerenciador;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private VotoRepository votoRepository;
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

    @PostMapping("/pauta/{idPauta}/associado/{idAssociado}/escolha/{escolha}")
    public ResponseEntity<?> cadastrarVoto(@PathVariable("idPauta") int idPauta, @PathVariable("idAssociado")int idAssociado, @PathVariable("escolha") char escolha)  {
        Voto obj = votoGerenciador.salvar(idPauta,idAssociado,escolha);
        if(controleTipoVoto==1) {
            controleTipoVoto = 0;
            return ResponseEntity.badRequest().body("Você deve votar [S] para sim ou [N] para não. Este voto não foi considerado.");
        }
        if (idPauta!=idPautaAberta) {
            return ResponseEntity.badRequest().body("Votação não esta aberta, não sendo possível salvar votos.");
        }
        if(msgVotoRealizado==1) {
            msgVotoRealizado=0;
            return ResponseEntity.ok().body(obj);}
        if(controleCPFvalidacao==1) {
            controleCPFvalidacao=0;
            return ResponseEntity.badRequest().body("CPF não é válido para votação: {\"status\":\"UNABLE_TO_VOTE\"}");}
        if(controleCpfUrl==1) {
            controleCpfUrl=0;
            return ResponseEntity.badRequest().body("Erro ao obter dados do CPF na URL.");}
        return ResponseEntity.badRequest().body("Voto já realizado!");
    }

}



