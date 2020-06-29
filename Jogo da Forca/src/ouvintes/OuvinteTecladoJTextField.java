package ouvintes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import telas.TelaDefault;

public class OuvinteTecladoJTextField implements KeyListener {

	private TelaDefault tela;
	
	public OuvinteTecladoJTextField(TelaDefault tela) {
		this.tela = tela;
	}
	
	public void keyPressed(KeyEvent arg0) {

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {
		
		char letra = arg0.getKeyChar();
		
		switch (letra) {
			case '~':
			case '^':
			case '`':
			case '´':
			
			case 'á':
			case 'à':
			case 'â':
			case 'ã':
				
			case 'é':
			case 'è':
			case 'ê':
			
			case 'í':
			case 'ì':
			case 'î':
				
			case 'ó':
			case 'ò':
			case 'ô':
				
			case 'ú':
			case 'ù':
			case 'û':
			
			case 'ç':
				arg0.consume();
				JOptionPane.showMessageDialog(tela, "Neste jogo, não é permitido qualquel acento ou letra acentuada que seja.");
		}
	}
}
