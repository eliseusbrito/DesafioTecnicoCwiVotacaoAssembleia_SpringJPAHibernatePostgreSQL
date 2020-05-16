package VotacaoAssembleia.gerenciador.exceptions;

public class ResourceFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceFoundException() {
        super("Associado já encontrado no cadastro.");
    }


}