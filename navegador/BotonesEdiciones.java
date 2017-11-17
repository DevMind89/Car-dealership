package navegador;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonesEdiciones extends JButton implements ConfiguracionBotones
{
	public JPanel panelBotonesEdiciones = null;
	public JButton anadir = null;	
	public JButton buscar = null;
	public JButton editar = null;
	public JButton borrar = null;
	
	public BotonesEdiciones() 
	{
		panelBotonesEdiciones = new JPanel();
		panelBotonesEdiciones.setBounds(350, 18, 276, 30);
		panelBotonesEdiciones.setLayout(new BoxLayout(panelBotonesEdiciones, BoxLayout.X_AXIS));
		
		anadir = new JButton();
		anadir.setBackground(ColorBotones);
		anadir.setIcon(new ImageIcon(getClass().getResource("/iconos/anadir.png")));
		anadir.setToolTipText("Añadir");
		panelBotonesEdiciones.add(anadir);
		
		buscar = new JButton();
		buscar.setBackground(ColorBotones);	
		buscar.setToolTipText("Buscar");
		buscar.setIcon(new ImageIcon(getClass().getResource("/iconos/buscar.png")));
		panelBotonesEdiciones.add(buscar);
		
		editar = new JButton();
		editar.setBackground(ColorBotones);
		editar.setToolTipText("Editar");
		editar.setIcon(new ImageIcon(getClass().getResource("/iconos/editar.png")));
		panelBotonesEdiciones.add(editar);
		
		borrar = new JButton();
		borrar.setBackground(ColorBotones);
		borrar.setToolTipText("Borrar");
		borrar.setIcon(new ImageIcon(getClass().getResource("/iconos/borrar.png")));
		panelBotonesEdiciones.add(borrar);
	}
}

