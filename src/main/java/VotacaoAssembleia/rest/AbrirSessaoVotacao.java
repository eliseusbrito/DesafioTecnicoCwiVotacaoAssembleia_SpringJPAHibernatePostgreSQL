package VotacaoAssembleia.rest;

import VotacaoAssembleia.acervo.PautaRepository;
import VotacaoAssembleia.dominio.Pauta;
import VotacaoAssembleia.dominio.Votacao;
import VotacaoAssembleia.gerenciador.PautaGerenciador;
import VotacaoAssembleia.gerenciador.VotacaoGerenciador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.*;

@RestController
@RequestMapping("/votacao")
public class AbrirSessaoVotacao {

    @Autowired
    VotacaoGerenciador abrirSessaoVotacaoGerenciador;
    @Autowired
    PautaRepository pautaRepository;
    @Autowired
    PautaGerenciador pautaGerenciador;

    @GetMapping("{id}")
    public Votacao tempoDefault(@PathVariable("id") int id)throws InterruptedException{
        abertaVotacao = true;
        List<Pauta> list = pautaGerenciador.findAll();
        if (pautaGerenciador.findById(id).getVotacao()!=null){
                return null;
        }
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
        List<Pauta> list = pautaGerenciador.findAll();
        if (pautaGerenciador.findById(id).getVotacao()!=null){
            return null;
        }
        idPautaAberta = id;
        System.out.println("A sessão de votação pauta id="+idPautaAberta+" ficará aberta por "+t+" minutos.");
        TimeUnit.MINUTES.sleep(t);
        System.out.println("Encerrado a votação id="+idPautaAberta+":");
        abertaVotacao = false;
        return abrirSessaoVotacaoGerenciador.contaVotos(idPautaAberta);
    }

}
