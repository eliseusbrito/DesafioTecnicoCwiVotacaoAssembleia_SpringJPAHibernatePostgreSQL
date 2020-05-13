package VotacaoAssembleia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DesafioTecnicoVotacaoAssembleia {

	public static boolean abertaVotacao = false;
	public static int idPautaAberta = 0;


	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoVotacaoAssembleia.class, args);
	}

}
