package entidades;

import exceptions.ValorNegativoException;

public class Usuario {
	
	private String nome, senha;
	private int pontuacao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) throws ValorNegativoException {
		if(pontuacao < 0)
			throw new ValorNegativoException();
			
		this.pontuacao = pontuacao;
	}
	
	public void pontuar() {
		pontuacao++;
	}
	
	public Usuario(String nome, String senha) throws ValorNegativoException {
		setNome(nome);
		setSenha(senha);
		setPontuacao(0);
	}
	
	public boolean equals(Usuario user) {
		return nome.equals(user.getNome());
	}
	
}
