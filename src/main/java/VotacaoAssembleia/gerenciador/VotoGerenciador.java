package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.*;
import VotacaoAssembleia.apiCpf.APIRest;
import VotacaoAssembleia.dominio.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static VotacaoAssembleia.DesafioTecnicoVotacaoAssembleia.*;

@Service
//@JsonSerialize
public class VotoGerenciador {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private AssociadoGerenciador associadoGerenciador;

    public List<Voto> findAll(){
        return votoRepository.findAll();
    }

    public Voto findById(Integer id){
        Optional<Voto> obj=votoRepository.findById(id);
        return obj.get();
    }

    public Voto salvar(int idPauta, int idAssociado, char escolha){
        String cpfAssociado = associadoGerenciador.findById(idAssociado).getCpf();
        APIRest apiRest = new APIRest();
        int situacaoCPF = apiRest.situacaoCPF(cpfAssociado);
        if (situacaoCPF == 20 ) {
            String str = "CPF não é válido para votação.";
            System.out.println(str);
            controleCPFvalidacao=1;
            return null;
        }
        if (situacaoCPF == 200 ) {
            String str = "Erro ao obter dados do CPF na URL.";
            System.out.println(str);
            controleCpfUrl=1;
            return null;
        }
        if (abertaVotacao== false ) {
            String str = "Votação id="+idPauta+" não esta aberta, não sendo possível salvar votos.";
            System.out.println(str);
            return null;
            }
        if (idPauta!=idPautaAberta) {
            System.out.println("Votação id="+idPauta+" não esta aberta, não sendo possível salvar votos.");
            return null;        }
        if (escolha=='S' || escolha=='N') {
            List<Voto> votos = findAll();
            for (Voto votoExistente : votos) {
                for (int i = 0; i < votos.size(); i++) {
                    if (votos.get(i).getPauta().getIdPauta()==idPauta&&votos.get(i).getAssociado().getId()==idAssociado) {
                        System.out.println("Voto já realizado nesta pauta "+idPauta+" pelo usuário "+ votos.get(i).getAssociado()+" . Este voto não foi considerado. ");
                        return votoExistente;
                    }
                }
            }
            System.out.println("pautaRepository.getOne(idPauta): "+pautaRepository.getOne(idPauta));
            System.out.println("associadoRepository.getOne(idAssociado): "+associadoRepository.getOne(idAssociado));
            Voto votoSemId = new Voto(pautaRepository.getOne(idPauta), associadoRepository.getOne(idAssociado), escolha);
            msgVotoRealizado = 1;
            return votoRepository.save(votoSemId);
        }
        else{
            System.out.println("Você deve votar [S] para sim ou [N] para não. Este voto não foi considerado.");
            controleTipoVoto=1;
            return null;}
    }
}

