package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.*;
import VotacaoAssembleia.dominio.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@JsonSerialize
public class VotoGerenciador {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private AssociadoRepository associadoRepository;

//    private VotoAcervo acervoVoto = new VotoAcervo();
//    private PautaAcervo acervoPauta = new PautaAcervo();
//    private AssociadoAcervo acervoAssociado = new AssociadoAcervo();

    public List<Voto> findAll(){
        return votoRepository.findAll();
    }

    public Voto findById(Integer id){
        Optional<Voto> obj=votoRepository.findById(id);
        return obj.get();
    }

      public Voto insert(Voto obj){
        return votoRepository.save(obj);
    }

    public Voto salvar(int idPauta, int idAssociado, char escolha){
//        Voto votoSemId = new Voto(pautaRepository.getOne(idPauta), associadoRepository.getOne(idAssociado), escolha);
        System.out.println("pautaRepository.getOne(idPauta): "+pautaRepository.getOne(idPauta));
        System.out.println("associadoRepository.getOne(idAssociado): "+associadoRepository.getOne(idAssociado));
        Voto votoSemId = new Voto(pautaRepository.getOne(idPauta), associadoRepository.getOne(idAssociado), escolha);
        return votoRepository.save(votoSemId);

//        return acervoVoto.salvar(votoSemId);

    }


//    public Voto salvar(int idPauta, int idAssociado, char escolha){
//        if (abertaVotacao== false ) {
//            System.out.println("Votação id="+idPauta+" não esta aberta, não sendo possível salvar votos.");
//            return null;        }
//        if (idPauta!=idPautaAberta) {
//            System.out.println("Votação id="+idPauta+" não esta aberta, não sendo possível salvar votos.");
//            return null;        }
//        if (escolha=='S' || escolha=='N') {
//            List<Voto> votos = acervoVoto.obter();
//            Pauta pauta = acervoPauta.pesquisar(idPauta);
//            Associado associado = acervoAssociado.pesquisar(idAssociado);
//            for (Voto votoExistente : votos) {
//                for (int i = 0; i < votos.size(); i++) {
//                    if (votos.get(i).getPauta().getIdPauta()==idPauta&&votos.get(i).getAssociado().getId()==idAssociado) {
//                        System.out.println("Voto já realizado nesta pauta "+idPauta+" pelo usuário "+ votos.get(i).getAssociado()+" . Este voto não foi considerado. ");
//                        return votoExistente;
//                    }
//                }
//            }
//            Voto votoSemId = new Voto(pauta, associado, escolha);
//            return acervoVoto.salvar(votoSemId);
//        }
//        else{
//            System.out.println("Você deve votar [S] para sim ou [N] para não. Este voto não foi considerado.");
//            return null;}
//    }

//    public List<Voto> listar() {
//        if (abertaVotacao== true) {
//            System.out.println("Votação em Andamento. Não é possível listar os votos");
//            return null;
//        }
//        return acervoVoto.listar();
//    }
    public void contaVotos() {
        List<Voto> list = findAll();
        int x = list.size();
        System.out.println("Votos: " + x);
//        for(Voto y : list){
        for (int i = 0; i < x; i++) {
            System.out.println(list.get(i).getEscolha());
            System.out.println(list);
        }
        System.out.println("Listou os votos");


        int idPauta = 1;
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
//        Pauta pauta = pautaGerenciador.findById(idPauta);
//        Decisao decisao = new Decisao(pauta, sim,nao,sim+nao,votacao);
//        System.out.println(decisao);

    }



}

