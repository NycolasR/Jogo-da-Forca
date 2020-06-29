package exceptions;

public class ValorNegativoException extends Exception {
	public ValorNegativoException() {
		super("ERRO! O valor não pode ser negativo");
	}
}
