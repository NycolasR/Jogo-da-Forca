package exceptions;

public class ValorNegativoException extends Exception {
	public ValorNegativoException() {
		super("ERRO! O valor n�o pode ser negativo");
	}
}
