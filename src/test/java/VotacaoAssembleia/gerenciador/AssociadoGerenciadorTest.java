package VotacaoAssembleia.gerenciador;

import VotacaoAssembleia.acervo.AssociadoRepository;
import VotacaoAssembleia.dominio.Associado;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
public class AssociadoGerenciadorTest {

    List<Associado> associadosDaAssociacao;
    int idAssociado1 = 1;
    int idAssociado2 = 2;
//    int idAssociado3 = 3;


    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private AssociadoGerenciador associadoGerenciador;

    @Before
    public void setUp(){
        System.out.println("Rodou before");
        Associado associado01 = new Associado("Antonio", "12345678900");
        Associado associado02 = new Associado("Pedro", "12345678911");
//        Associado associado03 = new Associado("Marieta", "32345678933");
        associado01.setId(idAssociado1);
        associado02.setId(idAssociado2);
//        associado03.setId(idAssociado3);
        /*Inserindo os associados na lista de Associados*/
        associadosDaAssociacao = new ArrayList<>();
        associadosDaAssociacao.add(associado01);
        associadosDaAssociacao.add(associado02);
//        associadosDaAssociacao.add(associado03);
    }

    @After
    public void tearDown(){
        associadosDaAssociacao.clear();
    }

    @Test
    public void findAll() {
        /*ensinando comportamento*/
        Mockito.when(associadoRepository.findAll()).thenReturn(associadosDaAssociacao);
        /*------ Ação - Roda o metodo que se quer testar -----*/
        List<Associado> associado = associadoGerenciador.findAll();
        /*----- Verificação -----*/
        verify(associadoRepository, times(1)).findAll();
        assertThat(associadosDaAssociacao.size(),is(2));
    }

    @Test
    public void findById() {
        /*ensinando comportamento*/
        Mockito.when(associadoRepository.findById(1)).thenReturn(Optional.of(associadosDaAssociacao.get(0)));
        /*------ Ação - Roda o metodo que se quer testar -----*/
        Associado associado = associadoGerenciador.findById(1);
        System.out.println("associado= "+associado);
        /*----- Verificação -----*/
        Assert.assertThat(associado.getId(),is(1));
        assertEquals(associado.getId(),1);
        Assert.assertTrue(associado.getId()==1);

    }

    @Test
    public void findByCpf() {
        /*------ Arrange ---------*/
        Mockito.when(associadoRepository.findAll()).thenReturn(associadosDaAssociacao);
        Mockito.when(associadoRepository.findById(1)).thenReturn(Optional.of(associadosDaAssociacao.get(0)));
        /*------ Act -----*/
        Associado associado = associadoGerenciador.findByCpf("12345678900");
        /*----- Assert -----*/
        Assert.assertThat(associado.getCpf(),is("12345678900"));
        assertEquals(associado.getCpf(),"12345678900");
    }

    @Test
    public void testIncluirNovoAssociado() {
        int idAssociado3 = 3;
        Associado associado03 = new Associado("Marieta", "32345678933");
        associado03.setId(idAssociado3);
//        associadosDaAssociacao.add(associado03);
        /*ensinando comportamento*/
        Mockito.when(associadoRepository.findAll()).thenReturn(associadosDaAssociacao);
        Mockito.when(associadoRepository.save(associado03)).thenReturn(associado03);
        /*------ Ação - Roda o metodo que se quer testar -----*/
        System.out.println("Denovo"+associado03);
        Associado associado = associadoGerenciador.insert(associado03);
        System.out.println(associadosDaAssociacao.size());
        Associado associadoNew = associadoGerenciador.findById(3);
        System.out.println("associadoNew= " + associadoNew);
        /*----- Verificação -----*/
//        verify(associadoRepository, times(1)).save(associado);
        assertThat(associado.getNome(),is("Marieta"));
        assertThat(associadosDaAssociacao.size(),is(3));
    }

    @Test
    public void delete() {
        /*-------- Arrange -------*/
//        Mockito.when(associadoRepository.deleteById(1).thenReturn(associadosDaAssociacao);
        /*-------- Act -------*/
        associadoRepository.deleteById(1);
        /*-------- Verify -------*/
//        assertThat(deleted, is(nullValue()));



        Assert.fail("Ainda não implementado");
    }

    @Test
    public void update() {
        Assert.fail("Ainda não implementado");
    }
}