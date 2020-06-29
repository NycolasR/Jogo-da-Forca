package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("ERRO! Usuário não encontrado.");
	}
}
