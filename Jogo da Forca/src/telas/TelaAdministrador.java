package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import entidades.Palavra;
import entidades.Usuario;
import util.Imagens;

public class TelaAdministrador extends TelaDefault {
	
	private TelaDefault tela = this;
	
	private class OuvinteBtnSair implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new TelaLogin();
		}
	}
	
	
	private class OuvinteBtnAdicionarPalavra implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			new TelaAddPalavras(tela);
		}
	}
	
	
	public TelaAdministrador() {
		super("Administração", 500, 515);
		
		addTables();
		addBotoes();
		
		setVisible(true);
	}
	
	private void addBotoes() {
		
		Font font = new Font("Arial", Font.PLAIN, 16);
		
		JButton btnSair = gerarBotao("Sair", 30, 435, 210, 35, new OuvinteBtnSair(), font, Imagens.ICONE_BACK);
		add(btnSair);
		
		JButton btnAdicionarPalavra = gerarBotao("Adicionar Palavra", 260, 435, 210, 35, new OuvinteBtnAdicionarPalavra(), font, Imagens.ICONE_PLUS);
		add(btnAdicionarPalavra);
	}

	private void addTables() {
		
		Usuario[] usuariosArray = getCentralDoJogo().getUsuarios().toArray(new Usuario[(getCentralDoJogo().getUsuarios().size())]);
		
		JScrollPane painelUsuarios = gerarTabela("Nome", "Senha", usuariosArray, 30, 30, 440, 185, true);
		add(painelUsuarios);
		
		Palavra[] palavrasArray = getCentralDoJogo().getPalavras().toArray(new Palavra[(getCentralDoJogo().getPalavras().size())]);
		
		JScrollPane painelPalavras = gerarTabela("Palavra", "Dica", palavrasArray, 30, 235, 440, 185, false);
		add(painelPalavras);
	}
}
