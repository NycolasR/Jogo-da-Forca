package exceptions;

public class PalavraDuplicadaException extends Exception {
	public PalavraDuplicadaException() {
		super("ERRO! Esta palavra j� est� na lista de palavras.");
	}
}
