package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("ERRO! Usu�rio n�o encontrado.");
	}
}
