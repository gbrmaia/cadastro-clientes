package controller;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GerarRelatorio {
	
	public GerarRelatorio() {
		Connection connection = Conexao.getInstancia().abrirConexao();
//		C:\Users\relam\OneDrive\Área de Trabalho\java\Clientes\src\controller\GerarRelatorio.java
		File file = new File("GerarRelatorio.java");
		String pathAbsoluto = file.getAbsolutePath();
		String pathAbsolutoParcial = pathAbsoluto.substring(0, pathAbsoluto.lastIndexOf('\\'))+"\\relatorios\\Coffee.jrxml";

		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(pathAbsolutoParcial);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(),connection);
			
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
			jasperViewer.setVisible(true);
			Conexao.getInstancia().fecharConexao();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
