package util;

import java.util.ArrayList;

import entidades.Palavra;
import entidades.Usuario;
import exceptions.PalavraDuplicadaException;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNaoEncontradoException;

public class CentralDoJogo {
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Palavra> palavras = new ArrayList<Palavra>();
	
	// Implementa��o do DP Singleton

	private static CentralDoJogo centralDoJogo;
	
	public static CentralDoJogo getInstance() {

		if(centralDoJogo == null) {
			centralDoJogo = PersistenciaXML.getInstance().recuperarCentralDoJogo();
		}

		return centralDoJogo;
	}
	
	// Gets e Sets
	
	public ArrayList<Palavra> getPalavras() {
		return palavras;
	}
	public void setPalavras(ArrayList<Palavra> palavras) {
		this.palavras = palavras;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	// M�todos para manipula��o do ArrayList<Usuario>
	
	public boolean isUsuario(Usuario user) {
		
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	public Usuario recuperarUsuario(String nome) throws UsuarioNaoEncontradoException {
		
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNome().equals(nome)) {
				return usuarios.get(i);
			}
		}
		throw new UsuarioNaoEncontradoException();
	}
	
	public void addUsuario(Usuario user) throws UsuarioDuplicadoException {
		
		if(isUsuario(user))
			throw new UsuarioDuplicadoException();
		
		usuarios.add(user);
	}
	
	// M�todos para manipula��o do ArrayList<Palavra>
	
	public boolean isPalavra(Palavra palavra) {
		
		for (int i = 0; i < palavras.size(); i++) {
			if (palavras.get(i).equals(palavra)) {
				return true;
			}
		}
		return false;
	}
	
	public void addPalavra(Palavra palavra) throws PalavraDuplicadaException {
		
		if(isPalavra(palavra))
			throw new PalavraDuplicadaException();
		
		palavras.add(palavra);
	}
	
	// M�todo respons�vel por retornar uma palavra aleat�ria do ArrayList<Palavra>
	
	public Palavra gerarPalavraAleatoria() {
		return palavras.get((int) (Math.random() * palavras.size()));
	}
}







