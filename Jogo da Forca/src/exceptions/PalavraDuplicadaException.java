package exceptions;

public class PalavraDuplicadaException extends Exception {
	public PalavraDuplicadaException() {
		super("ERRO! Esta palavra já está na lista de palavras.");
	}
}
