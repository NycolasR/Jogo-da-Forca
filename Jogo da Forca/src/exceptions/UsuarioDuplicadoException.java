package exceptions;

public class UsuarioDuplicadoException extends Exception {
	public UsuarioDuplicadoException() {
		super("ERRO! Usuário duplicado.");
	}
}
