package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.VotacaoRepository;
import VotacaoAssembleia.acervo.VotoRepository;
import VotacaoAssembleia.dominio.Votacao;
import VotacaoAssembleia.dominio.Pauta;
import VotacaoAssembleia.dominio.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotacaoGerenciador {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private VotoGerenciador votoGerenciador;
    @Autowired
    private PautaGerenciador pautaGerenciador;
    @Autowired
    private VotacaoRepository votacaoRepository;

    public Votacao contaVotos(int idPauta) {
        List<Voto> list = votoGerenciador.findAll();
        int x = list.size();
        System.out.println("Votos: " + x);
//        for(Voto y : list){
        for (int i = 0; i < x; i++) {
            System.out.println(list.get(i).getEscolha());
            System.out.println(list);
        }
        System.out.println("Listou os votos");


        System.out.println("Inciciou a Decisao");
        int sim = 0;
        int nao = 0;
        System.out.println("sim: " + sim);
        String votacao = "";

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPauta().getIdPauta() == idPauta && list.get(i).getEscolha() == 'S') {
                sim++;
            }
            if (list.get(i).getPauta().getIdPauta() == idPauta && list.get(i).getEscolha() == 'N') {
                nao++;
            }
        }
        System.out.println("Total de Votos: " + (sim + nao));
        System.out.println("Votos Sim: " + sim);
        System.out.println("Votos Não: " + nao);
        if (sim > nao) {
            votacao = "Pauta id=" + idPauta + " Aprovada!";
        }
        if (sim < nao) {
            votacao = "Pauta id=" + idPauta + " Não Aprovada!";
        }
        if (sim == nao) {
            votacao = "Pauta id=" + idPauta + " precisa nova votação!";
        }
        System.out.println("Decisão: " + votacao);
        Pauta pauta = pautaGerenciador.findById(idPauta);
        Votacao decisao = new Votacao(pauta, sim,nao,sim+nao,votacao);
        System.out.println(decisao);
        votacaoRepository.save(decisao);

        return decisao;
    }
}
