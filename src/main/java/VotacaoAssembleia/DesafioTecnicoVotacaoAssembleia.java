package VotacaoAssembleia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DesafioTecnicoVotacaoAssembleia {

	public static boolean abertaVotacao = false;
	public static int idPautaAberta = 0;
	public static int controleCadastroExistente = 0;
	public static int controleTipoVoto = 0;
	public static int msgVotoRealizado = 0;
	public static int controleCPFvalidacao = 0;
	public static int controleCpfUrl = 0;


	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoVotacaoAssembleia.class, args);
	}

}
