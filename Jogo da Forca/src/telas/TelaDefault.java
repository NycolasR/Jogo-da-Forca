package telas;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import entidades.Administrador;
import entidades.Palavra;
import entidades.Usuario;
import util.CentralDoJogo;
import util.Imagens;
import util.PersistenciaXML;

import javax.swing.UIManager.LookAndFeelInfo;

public class TelaDefault extends JFrame {
	
	private CentralDoJogo centralDoJogo = CentralDoJogo.getInstance();
	private PersistenciaXML persistenciaXML = PersistenciaXML.getInstance();
	
	public PersistenciaXML getPersistenciaXML() {
		return persistenciaXML;
	}
	public void setPersistenciaXML(PersistenciaXML persistenciaXML) {
		this.persistenciaXML = persistenciaXML;
	}

	public CentralDoJogo getCentralDoJogo() {
		return centralDoJogo;
	}
	public void setCentralDoJogo(CentralDoJogo centralDoJogo) {
		this.centralDoJogo = centralDoJogo;
	}

	public TelaDefault(String title, int width, int height) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// handle exception
		}
		
		setIconImage(Imagens.ICONE_FORCA.getImage());
		setSize(width, height);
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
	}

	protected JButton gerarBotao(String title, int x, int y, int width, int height, ActionListener listener, Font font, ImageIcon icon) {
		JButton btn = new JButton(title);
		btn.setBounds(x, y, width, height);
		btn.setFont(font);
		btn.setIcon(icon);
		btn.addActionListener(listener);
		return btn;
	}

	protected JLabel gerarLabel(String title, int x, int y, int width, int height, int alignment, Font font) {
		JLabel lbl = new JLabel(title);
		lbl.setBounds(x, y, width, height);
		lbl.setHorizontalAlignment(alignment);
		lbl.setFont(font);
		return lbl;
	}

	protected JLabel gerarLabel(String title, int x, int y, int width, int height, int alignment, Font font, Icon icon) {
		JLabel lbl = gerarLabel(title, x, y, width, height, alignment, font);
		lbl.setIcon(icon);
		return lbl;
	}
	
	protected JLabel gerarLabel(Icon icon, int x, int y, int width, int height) {
		JLabel lbl = new JLabel(icon);
		lbl.setBounds(x, y, width, height);
		return lbl;
	}

	protected JTextField gerarTextField(String text, int x, int y, int width, int height) {
		JTextField tfd = new JTextField();
		tfd.setToolTipText(text);
		tfd.setBounds(x, y, width, height);
		return tfd;
	}
	
	protected JScrollPane gerarTabela(String coluna1, String coluna2, Object[] arrayList, int x, int y, int width, int height, boolean isUserTable) {
		
		JTable tabela = new JTable();
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn(coluna1);
		modelo.addColumn(coluna2);
		
		for (int i = 0; i < arrayList.length; i++) {
			
			Object[] linha = new Object[2];
			
			if(isUserTable) {
				
				if(i == 0) {
					modelo.addColumn("Pontuação Total");	
				}
				linha = new Object[3];
				
			}
				
			if (arrayList[i] instanceof Usuario) {
				
				Usuario user = (Usuario) arrayList[i]; 
				
				linha[0] = user.getNome();
				linha[1] = user.getSenha();
				linha[2] = user.getPontuacao();
				
				if (user instanceof Administrador) {
					linha[2] = "~";
				}
						
			} else {
				
				Palavra palavra = (Palavra) arrayList[i];
				
				linha[0] = palavra.getPalavra();
				linha[1] = palavra.getDica();
						
			}
			
			modelo.addRow(linha);
		}
		
		tabela.setModel(modelo);
		JScrollPane painel = new JScrollPane(tabela);
		painel.setBounds(x, y, width, height);
		return painel;
	}
	
	protected JSeparator gerarSeparador(int x, int y, int width, int height) {
		JSeparator separator = new JSeparator();
		separator.setBounds(x, y, width, height);
		return separator;
	}
	
	protected JSeparator gerarSeparator(int x, int y, int width, int height, int orientation) {
		JSeparator separator = gerarSeparador(x, y, width, height);
		separator.setOrientation(orientation);
		return separator;
	}
}