package exceptions;

public class UsuarioDuplicadoException extends Exception {
	public UsuarioDuplicadoException() {
		super("ERRO! Usu�rio duplicado.");
	}
}
