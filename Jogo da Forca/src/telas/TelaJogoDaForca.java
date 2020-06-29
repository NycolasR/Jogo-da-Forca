package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidades.*;
import ouvintes.OuvinteFocoJTextField;
import ouvintes.OuvinteTecladoJTextField;
import util.Imagens;

public class TelaJogoDaForca extends TelaDefault {
 
	private Usuario usuario;
	private Palavra palavraSorteada;
	private JLabel lblGabarito;
	private String strGabarito;
	private JTextField tfdLetra;
	private Font font = new Font("Arial", Font.PLAIN, 18);
	private TelaDefault tela = this;
	private ArrayList<Character> letrasTentadas = new ArrayList<Character>();
	private JLabel[] lblPartesDoCorpo = new JLabel[6];
	private int contadorDeErros;

	
	private class OuvinteBtnTestarLetra implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(tfdLetra.getText().isEmpty()) {
				
				JOptionPane.showMessageDialog(tela, "Informe uma letra no campo de texto!");
				
			} else if(tfdLetra.getText().length() > 1) {
				
				JOptionPane.showMessageDialog(tela, "Você só pode informar uma letra de cada vez!");
				
			} else {
				
				String strLetra = tfdLetra.getText().toLowerCase();
				char chLetra = strLetra.charAt(0);
				letrasTentadas.add(chLetra);
				
				tfdLetra.setText("");
				repaint();
				
				String strTemporaria = "";
				String strPalavra = palavraSorteada.getPalavra();
				
				for(int i = 0; i < strPalavra.length(); i++) {
					
					if(chLetra == strPalavra.charAt(i)) {
						
						strTemporaria += chLetra;
						
					} else {
						
						strTemporaria += strGabarito.charAt(i);
						
					}
				}
				
				if(strTemporaria.equals(strGabarito)) {
					
					add(lblPartesDoCorpo[contadorDeErros++]);
					repaint();
					
					if(contadorDeErros == lblPartesDoCorpo.length) {
						JOptionPane.showMessageDialog(tela, "Você perdeu! Tente novamente quando quiser.");
						reinicarJogo();
					}
					
				} else {
					
					strGabarito = strTemporaria;
					lblGabarito.setText(strGabarito);
					
					if(isOver()) {
						usuario.pontuar();
						getPersistenciaXML().salvarCentralDoJogo(getCentralDoJogo());
						JOptionPane.showMessageDialog(tela, "Parabéns! Você venceu o Jogo da Forca!");
						reinicarJogo();
					}
				}
			}
		}
	}
	
	
	
	public TelaJogoDaForca(Usuario usuario) {
		super("Jogo da Forca", 600, 500);
		this.usuario = usuario;
		
		addLabels();
		addBotoes();
		addTextFields();
		addSeparadores();
		desenharForcaECorpo();
		
		setVisible(true);
	}
	
	private void addSeparadores() {
		JSeparator separador1 = gerarSeparador(0, 70, 600, 8);
		add(separador1);
		
		JSeparator separador2 = gerarSeparador(0, 73, 600, 8);
		add(separador2);
		
		JSeparator separador3 = gerarSeparador(0, 423, 600, 8);
		add(separador3);
		
		JSeparator separador4 = gerarSeparador(0, 426, 600, 8);
		add(separador4);
		
		JSeparator separador5 = gerarSeparator(229, 74, 8, 351, SwingConstants.VERTICAL);
		add(separador5);
		
		JSeparator separador6 = gerarSeparator(232, 74, 8, 351, SwingConstants.VERTICAL);
		add(separador6);
	}

	private void addLabels() {
		JLabel lblPrincipal = gerarLabel("Bem vindo ao Jogo da Forca!", 0, 15, 600, 50, JLabel.CENTER, new Font("Arial Narrow", Font.PLAIN, 40));
		add(lblPrincipal);
		
		gerarGabaritoInicial();
		
		lblGabarito = gerarLabel(strGabarito, 0, 430, 600, 35, JLabel.CENTER, new Font("Aral Narrow", Font.ITALIC, 30));
		add(lblGabarito);
		
		JLabel lblDigiteUmaLetra = gerarLabel("Digite uma letra:", 320, 110, 130, 30, JLabel.LEFT, font);
		add(lblDigiteUmaLetra);
	}
	
	private void gerarGabaritoInicial() {
		
		palavraSorteada = getCentralDoJogo().gerarPalavraAleatoria();
		String strPalavraSorteada = palavraSorteada.getPalavra();
		strGabarito = "";
		lblGabarito = new JLabel();
		
		for(int i = 0; i < strPalavraSorteada.length(); i++) {
			strGabarito += "_"; 
		}
	}
	
	private boolean isOver() {
		
		boolean isOver = true;
		
		for(int i = 0; i < strGabarito.length(); i++) {
			if(strGabarito.charAt(i) == '_') {
				isOver = false;
				break;
			}
		}
		return isOver;
	}

	private void addBotoes() {
		JButton btnTestarLetra = gerarBotao("Testar Letra", 315, 160, 200, 45, new OuvinteBtnTestarLetra(), font, Imagens.ICONE_OK);
		add(btnTestarLetra);
		
		JButton btnLetrasTentadas = gerarBotao("Letras Tentadas", 315, 225, 200, 45, null, font, Imagens.ICONE_LISTA_24);
		
		btnLetrasTentadas.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Character[] strLetrasTentadas = letrasTentadas.toArray(new Character[letrasTentadas.size()]);
				JOptionPane.showMessageDialog(tela, Arrays.toString(strLetrasTentadas), "Lista de Letras Tentadas", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		add(btnLetrasTentadas);
		
		JButton btnMostrarDica = gerarBotao("Mostrar Dica", 315, 290, 200, 45, null, font, Imagens.ICONE_LAMP_24);
		
		btnMostrarDica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(tela, palavraSorteada.getDica(), "Dica Para a Palavra", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		add(btnMostrarDica);
		
		JButton btnSair = gerarBotao("Sair", 315, 355, 200, 45, null, font, Imagens.ICONE_BACK);
		
		btnSair.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaLogin();
			}
		});
		
		add(btnSair);
	}

	private void addTextFields() {
		tfdLetra = gerarTextField("Insira uma letra", 460, 110, 40, 30);
		tfdLetra.addFocusListener(new OuvinteFocoJTextField(tfdLetra));
		tfdLetra.addKeyListener(new OuvinteTecladoJTextField(tela));
		add(tfdLetra);
	}

	private void desenharForcaECorpo() {
		
		// Partes da forca
		
		JLabel lblForca1 = gerarLabel(Imagens.ICONE_VERTICAL_LINE_50, 105, 83, 10, 50);
		add(lblForca1);
		
		JLabel lblForca2 = gerarLabel(Imagens.ICONE_LINHA_HORIZONTAL_100, 16, 83, 100, 10);
		add(lblForca2);
		
		JLabel lblForca3 = gerarLabel(Imagens.ICONE_VERTICAL_LINE_100, 16, 82, 10, 100);
		add(lblForca3);
		
		JLabel lblForca4 = gerarLabel(Imagens.ICONE_VERTICAL_LINE_100, 16, 174, 10, 100);
		add(lblForca4);
		
		JLabel lblForca5 = gerarLabel(Imagens.ICONE_VERTICAL_LINE_100, 16, 226, 10, 100);
		add(lblForca5);
		
		JLabel lblForca6 = gerarLabel(Imagens.ICONE_VERTICAL_LINE_100, 16, 300, 10, 100);
		add(lblForca6);
		
		JLabel lblForca7 = gerarLabel(Imagens.ICONE_LINHA_HORIZONTAL_100, 0, 390, 25, 10);
		add(lblForca7);
		
		// Partes do corpo
		
		JLabel lblHead = gerarLabel(Imagens.ICONE_CIRCLE, 80, 126, 60, 60);
		lblPartesDoCorpo[0] = lblHead;
		
		JLabel lblBody = gerarLabel(Imagens.ICONE_VERTICAL_LINE_100, 105, 183, 10, 85);
		lblPartesDoCorpo[1] = lblBody;
		
		JLabel lblLeftArm = gerarLabel(Imagens.ICONE_LINHA_DIAGONAL_UP_50, 59, 200, 50, 50);
		lblPartesDoCorpo[2] = lblLeftArm;
		
		JLabel lblLeftLeg = gerarLabel(Imagens.ICONE_LINHA_DIAGONAL_UP_50, 59, 265, 50, 50);
		lblPartesDoCorpo[3] = lblLeftLeg;
		
		JLabel lblRightArm = gerarLabel(Imagens.ICONE_LINHA_DIAGONAL_DOWN_50, 110, 200, 50, 50);
		lblPartesDoCorpo[4] = lblRightArm;
		
		JLabel lblRightLeg = gerarLabel(Imagens.ICONE_LINHA_DIAGONAL_DOWN_50, 110, 265, 50, 50);
		lblPartesDoCorpo[5] = lblRightLeg;
	}
	
	private void reinicarJogo() {
		dispose();
		new TelaJogoDaForca(usuario);
	}
	
}
