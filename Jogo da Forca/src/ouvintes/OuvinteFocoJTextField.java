package ouvintes;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class OuvinteFocoJTextField implements FocusListener {
	
	private JComponent componente;
	
	public OuvinteFocoJTextField(JComponent comp) {
		this.componente = comp;
	}
	
	public void focusGained(FocusEvent arg0) {
		JTextField tfd = (JTextField) componente;
		tfd.setBackground(Color.WHITE);
	}
	
	public void focusLost(FocusEvent e) {
		JTextField tfd = (JTextField) componente;
		
		if (tfd.getText().isEmpty())
			tfd.setBackground(Color.RED);
		else
			tfd.setBackground(Color.GREEN);
	}
	
}
