package entidades;

public class Palavra {
	
	private String palavra, dica;

	public String getPalavra() {
		return palavra;
	}
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getDica() {
		return dica;
	}
	public void setDica(String dica) {
		this.dica = dica;
	}
	
	public String toString() {
		return palavra;
	}
	
	public boolean equals(Palavra palavra) {
		return this.palavra.equals(palavra.getPalavra());
	}
	
	public Palavra(String palavra, String dica) {
		this.palavra = palavra;
		this.dica = dica;
	}
}
