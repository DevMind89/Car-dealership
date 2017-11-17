package navegador;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonesFlechas extends JButton implements ConfiguracionBotones
{
	public JPanel panelBotonesFlechas = null;
	public JButton primero = null;	
	public JButton anterior = null;
	public JButton siguiente = null;
	public JButton ultimo = null;
	
	public BotonesFlechas() 
	{
		panelBotonesFlechas = new JPanel();
		panelBotonesFlechas.setBounds(80, 380, 276, 30);
		panelBotonesFlechas.setLayout(new BoxLayout(panelBotonesFlechas, BoxLayout.X_AXIS));
		
		primero = new JButton();
		primero.setBackground(ColorBotones);
		primero.setIcon(new ImageIcon(getClass().getResource("/iconos/primero.png")));
		primero.setToolTipText("Ir al primero");
		panelBotonesFlechas.add(primero);
		
		anterior = new JButton();
		anterior.setBackground(ColorBotones);	
		anterior.setToolTipText("Anterior");
		anterior.setIcon(new ImageIcon(getClass().getResource("/iconos/anterior.png")));
		panelBotonesFlechas.add(anterior);
		
		siguiente = new JButton();
		siguiente.setBackground(ColorBotones);
		siguiente.setToolTipText("Siguiente");
		siguiente.setIcon(new ImageIcon(getClass().getResource("/iconos/siguiente.png")));
		panelBotonesFlechas.add(siguiente);
		
		ultimo = new JButton();
		ultimo.setBackground(ColorBotones);
		ultimo.setToolTipText("Ir al ultimo");
		ultimo.setIcon(new ImageIcon(getClass().getResource("/iconos/ultimo.png")));
		panelBotonesFlechas.add(ultimo);
	}
}
