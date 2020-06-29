
package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidades.Palavra;
import exceptions.PalavraDuplicadaException;
import ouvintes.OuvinteFocoJTextField;
import ouvintes.OuvinteTecladoJTextField;
import util.Imagens;

public class TelaAddPalavras extends TelaDefault {

	private JTextField tfdPalavra;
	private JTextField tfdDica;
	private Font font = new Font("Arial", Font.PLAIN, 16);
	private TelaDefault tela = this;
	private TelaDefault telaAdmin;
	
	private class OuvinteBtnAddPalavra implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(tfdPalavra.getText().isEmpty() || tfdDica.getText().isEmpty()) {
			
				JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
				
			} else if(tfdPalavra.getText().length() > 13) {
				
				JOptionPane.showMessageDialog(tela, "O limite de carateres estabelecido é 13. Escolha uma palavra menor.");
				
			} else {

				String palavraStr = tfdPalavra.getText().toLowerCase();
				String dicaStr = tfdDica.getText();

				Palavra palavra = new Palavra(palavraStr, dicaStr);

				try {

					getCentralDoJogo().addPalavra(palavra);
					getPersistenciaXML().salvarCentralDoJogo(getCentralDoJogo());

					JOptionPane.showMessageDialog(tela, "Palavra adicionada com sucesso.");
					
					dispose();
					telaAdmin.dispose();
					new TelaAdministrador();

				} catch (PalavraDuplicadaException e1) {

					JOptionPane.showMessageDialog(tela, e1.getMessage());
				}
			}
		}
	}
	
	
	public TelaAddPalavras(TelaDefault telaAdmin) {
		super("Adicione uma Palavra", 400, 265);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.telaAdmin = telaAdmin;
		
		addLabels();
		addBotoes();
		addTextFields();
		
		setVisible(true);
	}
	
	private void addTextFields() {
		
		tfdPalavra = gerarTextField("Informe uma palavra", 115, 27, 225, 30);
		tfdPalavra.addFocusListener(new OuvinteFocoJTextField(tfdPalavra));
		tfdPalavra.addKeyListener(new OuvinteTecladoJTextField(tela));
		add(tfdPalavra);
		
		tfdDica = gerarTextField("Informe uma dica", 115, 85, 225, 30);
		tfdDica.addFocusListener(new OuvinteFocoJTextField(tfdDica));
		tfdDica.addKeyListener(new OuvinteTecladoJTextField(tela));
		add(tfdDica);
		
	}

	private void addLabels() {
		JLabel lblPalavra = gerarLabel("Palavra", 50, 27, 55, 30, JLabel.LEFT, font);
		add(lblPalavra);

		JLabel lblDica = gerarLabel("Dica", 50, 85, 55, 30, JLabel.CENTER, font);
		add(lblDica);
	}

	private void addBotoes() {
		
		JButton btnLimparCampos = gerarBotao("Limpar", 145, 135, 110, 35, null, font, Imagens.ICONE_ERASE);
		
		btnLimparCampos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				tfdPalavra.setText("");
				tfdDica.setText("");
			}
		});
		
		add(btnLimparCampos);
		
		JButton btnAdicionar = gerarBotao("Adicionar Palavra", 100, 185, 200, 35, new OuvinteBtnAddPalavra(), font, Imagens.ICONE_PLUS);
		add(btnAdicionar);
	}
}
