package informes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

public class ImprimirPDF
{  
   public Font fuenteInforme = new Font("Dialog", Font.PLAIN, 10);
   public PrintJob pj;
   public Graphics paginaInforme;

	public ImprimirPDF()
	{
		pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "Informe", null);
	}
 
    public void realizarImpresion(String textoImprimir)
	{
		try
		{
			paginaInforme = pj.getGraphics();			
			paginaInforme.setFont(fuenteInforme);
			paginaInforme.setColor(Color.black);
 
			paginaInforme.drawString(textoImprimir, 60, 80);			
 
			paginaInforme.dispose();
			pj.end();
		}catch(Exception e){JOptionPane.showMessageDialog(null, "Impresión cancelada");}
	}	 
}