package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;

public class PersistenciaXML {
	
	private XStream xstream;
	
	private static PersistenciaXML persistenciaXML;
	
	public static PersistenciaXML getInstance() {
		
		if (persistenciaXML == null) {
			persistenciaXML = new PersistenciaXML();
		}
		
		return persistenciaXML;
	}
	
	public PersistenciaXML() {
		xstream = new XStream(new DomDriver("utf-8"));
		xstream.addPermission(NoTypePermission.NONE);
		xstream.allowTypesByRegExp(new String[] { "util.*", "entidades.*" });
	}
	
	public void salvarCentralDoJogo(CentralDoJogo cdj) {
		
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		xml.append(System.getProperty("line.separator"));
		xml.append(xstream.toXML(cdj));
		
		File arquivoXML = new File("centralDoJogo.xml");
		
		try {
			
			arquivoXML.createNewFile();
			PrintWriter gravador = new PrintWriter(arquivoXML);
			gravador.print(xml);
			gravador.close();
			
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
	}
	
	public CentralDoJogo recuperarCentralDoJogo() {
		
		File arquivoXML = new File("centralDoJogo.xml");
		
		try {
			
			if(arquivoXML.exists()) {
				
				FileInputStream fis = new FileInputStream(arquivoXML);
				return (CentralDoJogo) xstream.fromXML(fis);
				
			}
			
		} catch (FileNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		
		return new CentralDoJogo();
	}
}
