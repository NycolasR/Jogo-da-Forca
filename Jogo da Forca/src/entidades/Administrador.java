package entidades;

import exceptions.ValorNegativoException;

public class Administrador extends Usuario {
	
	public Administrador(String nome, String senha) throws ValorNegativoException {
		super(nome, senha);
	}
	
}
