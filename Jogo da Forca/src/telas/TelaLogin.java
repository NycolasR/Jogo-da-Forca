package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidades.Administrador;
import entidades.Usuario;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNaoEncontradoException;
import exceptions.ValorNegativoException;
import ouvintes.OuvinteFocoJTextField;
import ouvintes.OuvinteTecladoJTextField;
import util.Imagens;

public class TelaLogin extends TelaDefault {
	
	private JTextField tfdNome;
	private JPasswordField tfdSenha;
	private TelaDefault tela = this;
	private JButton btnEntrar;
	private JButton btnResetarSenha;
	
	private class OuvinteBtnNovo implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String nome = tfdNome.getText();
			String senha = tfdSenha.getText();

			if (nome.isEmpty() || senha.isEmpty()) {

				JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");

			} else {
				
				Usuario user = null;
				
				try {
					
					if (getCentralDoJogo().getUsuarios().isEmpty()) {
						user = new Administrador(nome, senha);
					} else {
						user = new Usuario(nome, senha);
					}
					
					getCentralDoJogo().addUsuario(user);
					
					getPersistenciaXML().salvarCentralDoJogo(getCentralDoJogo());					
					JOptionPane.showMessageDialog(tela, "Cadastro bem sucedido.");
					
					entrarNoJogo(user);
					
				} catch (UsuarioDuplicadoException | ValorNegativoException e1) {
					JOptionPane.showMessageDialog(tela, e1.getMessage());
				}
			}
		}
	}
	
	
	
	private class OuvinteBtnEntrar implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String nome = tfdNome.getText();
			String senha = tfdSenha.getText();

			if (nome.isEmpty() || senha.isEmpty()) {

				JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
			
			} else {
				
				Usuario user = null;
				
				try {
					user = getCentralDoJogo().recuperarUsuario(nome);
					
					if(!senha.equals(user.getSenha())) {
						
						JOptionPane.showMessageDialog(tela, "Senha Incorreta");
						
					} else {
						
						entrarNoJogo(user);
						
					}

				} catch (UsuarioNaoEncontradoException e1) {
					JOptionPane.showMessageDialog(tela, "ERRO! Usuário não cadastrado.");
				}
			}
		}
	}
	
	
	private class OuvinteBtnResetarSenha implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String nome = JOptionPane.showInputDialog(tela, "Informe Seu Nome");
			
			try {
				
				Usuario user = getCentralDoJogo().recuperarUsuario(nome);
				
				user.setSenha(nome);
				
				getPersistenciaXML().salvarCentralDoJogo(getCentralDoJogo());	
				
				JOptionPane.showMessageDialog(tela, "Agora, sua senha está igual ao seu nome.");
				
			} catch (UsuarioNaoEncontradoException e1) {
				
				JOptionPane.showMessageDialog(tela, e1.getMessage());
			}	
		}
	}
	
	
	public TelaLogin() {
		super("Login e Cadastro de Usuário", 430, 500);
		
		addLabels();
		addBotoes();
		addTextFields();
		
		if(getCentralDoJogo().getUsuarios().isEmpty()) {
			btnEntrar.setEnabled(false);
			btnResetarSenha.setEnabled(false);
		}
		
		setVisible(true);
	}

	private void addLabels() {
		JLabel lblTitle = gerarLabel("Jogo da Forca", 0, 50, 430, 55, JLabel.CENTER, new Font("Arial Narrow", Font.PLAIN, 50), Imagens.ICONE_FORCA);
		add(lblTitle);
		
		JLabel lblFaixa = gerarLabel("", 0, 70, 430, 22, JLabel.CENTER, null);
		lblFaixa.setOpaque(true);
		lblFaixa.setBackground(Color.LIGHT_GRAY);
		add(lblFaixa);
		
		Font font = new Font("Arial", Font.PLAIN, 16);
		
		JLabel lblNome = gerarLabel("Nome", 80, 215, 50, 30, JLabel.LEFT, font);
		add(lblNome);
		
		JLabel lblSenha = gerarLabel("Senha", 80, 265, 50, 30, JLabel.LEFT, font);
		add(lblSenha);
		
		String strInstrucao = "Cadastre-se Para Jogar ou Faça Login";
		
		if(getCentralDoJogo().getUsuarios().isEmpty())
			strInstrucao = "Cadastre-se Como Administrador";
		
		JLabel lblInstrucao = gerarLabel(strInstrucao, 0, 130, 430, 40, JLabel.CENTER, new Font("Arial", Font.ITALIC, 20));
		add(lblInstrucao);
	}

	private void addBotoes() {
		
		Font font = new Font("Arial", Font.PLAIN, 16);
		
		btnEntrar = gerarBotao("Entrar", 105, 325, 100, 35, new OuvinteBtnEntrar(), font, Imagens.ICONE_OK);
		add(btnEntrar);
		
		JButton btnNovo = gerarBotao("Novo", 225, 325, 100, 35, new OuvinteBtnNovo(), font, Imagens.ICONE_USER);
		add(btnNovo);
		
		btnResetarSenha = gerarBotao("Resetar Senha", 105, 380, 220, 35, new OuvinteBtnResetarSenha(), font, Imagens.ICONE_RESET);
		add(btnResetarSenha);
	}
	
	private void entrarNoJogo(Usuario user) {
		
		dispose();
		if (user instanceof Administrador) {
			new TelaAdministrador();
		} else if(getCentralDoJogo().getPalavras().isEmpty()){
			JOptionPane.showMessageDialog(tela, "Não há palavras cadastradas. Entre em contato com o administrador do jogo.");
			new TelaLogin();
		} else {
			new TelaJogoDaForca(user);						
		}
		
	}

	private void addTextFields() {
		tfdNome = gerarTextField("Informe seu nome aqui", 135, 215, 200, 30);
		tfdNome.addFocusListener(new OuvinteFocoJTextField(tfdNome));
		tfdNome.addKeyListener(new OuvinteTecladoJTextField(tela));
		add(tfdNome);
		
		tfdSenha = new JPasswordField();
		tfdSenha.addFocusListener(new OuvinteFocoJTextField(tfdSenha));
		tfdSenha.setToolTipText("Informe sua senha aqui");
		tfdSenha.setBounds(135, 265, 200, 30);
		tfdSenha.addKeyListener(new OuvinteTecladoJTextField(tela));
		add(tfdSenha);
	}
}
