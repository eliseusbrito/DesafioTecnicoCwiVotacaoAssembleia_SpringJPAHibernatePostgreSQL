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

}

