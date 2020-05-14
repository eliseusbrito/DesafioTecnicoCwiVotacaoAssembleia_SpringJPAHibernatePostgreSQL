package VotacaoAssembleia.config;

import VotacaoAssembleia.acervo.AssociadoRepository;
import VotacaoAssembleia.acervo.PautaRepository;
import VotacaoAssembleia.acervo.VotoRepository;
import VotacaoAssembleia.dominio.Associado;
import VotacaoAssembleia.dominio.Pauta;
import VotacaoAssembleia.dominio.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Override
    public void run(String... args) throws Exception {
        Associado u1 = new Associado("Maria Brown", "66666666666");
        Associado u2 = new Associado( "Alex Green", "12345678900");
        Associado u3 = new Associado( "Pedrinho", "22345678900");
//        Associado u4 = new Associado( "Davi", "32345678900");
        associadoRepository.saveAll(Arrays.asList(u1,u2,u3));
//        associadoRepository.saveAll(Arrays.asList(u1,u2,u3,u4));

        Pauta p1 = new Pauta("Votacao para aumentar numero de Sócios");
        Pauta p2 = new Pauta( "Reduzir taxa de juros dos Financiamentos");
        Pauta p3 = new Pauta( "Aprovar verba para propaganda");
        pautaRepository.saveAll(Arrays.asList(p1,p2,p3));

        Voto v1 = new Voto(p1,u1, 'S');
        Voto v2 = new Voto(p1,u2, 'S');
        Voto v3 = new Voto(p1,u3, 'N');
        Voto v4 = new Voto(p2,u1, 'S');
        Voto v5 = new Voto(p2,u2, 'N');
        votoRepository.saveAll(Arrays.asList(v1,v2,v3,v4,v5));
    }



}
