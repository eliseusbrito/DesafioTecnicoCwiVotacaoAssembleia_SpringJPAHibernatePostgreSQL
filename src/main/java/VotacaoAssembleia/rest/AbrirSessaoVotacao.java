package VotacaoAssembleia.rest;

import VotacaoAssembleia.dominio.Votacao;
import VotacaoAssembleia.gerenciador.VotacaoGerenciador;
import VotacaoAssembleia.rest.exceptions.StandardError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.*;

@RestController
@RequestMapping("/votacao")
public class AbrirSessaoVotacao {

    @Autowired
    VotacaoGerenciador abrirSessaoVotacaoGerenciador;

    @GetMapping("{id}")
    public Votacao tempoDefault(@PathVariable("id") int id)throws InterruptedException{
        abertaVotacao = true;
        if (votacaoRealizada==1){return null;}
        int votacaoRealizada = 1;
        idPautaAberta = id;
        System.out.println("A sessão de votação pauta id="+idPautaAberta+" ficará aberta por 1 minuto.");
        TimeUnit.SECONDS.sleep(60);
        System.out.println("Encerrado a votação id="+idPautaAberta);
        abertaVotacao = false;
        return abrirSessaoVotacaoGerenciador.contaVotos(idPautaAberta);
    }

    @GetMapping("{id}/tempo/{t}")
    public Votacao tempoVariavel(@PathVariable("id") int id, @PathVariable("t") int t) throws InterruptedException {
        abertaVotacao = true;
        idPautaAberta = id;
        System.out.println("A sessão de votação pauta id="+idPautaAberta+" ficará aberta por "+t+" minutos.");
        TimeUnit.MINUTES.sleep(t);
        System.out.println("Encerrado a votação id="+idPautaAberta+":");
        abertaVotacao = false;
        return abrirSessaoVotacaoGerenciador.contaVotos(idPautaAberta);
    }

}
