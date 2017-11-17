package informes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import bbdd.ConexionBD;
import consultasSQL.ConsultasClientes;
import consultasSQL.ConsultasCoches;
import consultasSQL.ConsultasInformes;

public class FormularioInformes 
{
	public JPanel panelPrincipal = null;
	
	JLabel tituloFormulario = null;
	JLabel Clientes = null;
	JLabel Matricula = null;
	
	JComboBox<String> jComboCodigoCliente = null;
	JComboBox<String> jComboMatricula = null;
	
	JButton botonImprimir = null;
	JButton botonLimpiar = null;
	JButton botonBuscarClientes = null;
	JButton botonBuscarMatriculas  =null;
	
	JTextArea cuadroInforme = null;
	
	JScrollPane scroll = null;
	
	ConexionBD objetoBBDD = null;
	ConsultasCoches objConsultasCoches = null;
	ConsultasClientes objConsultasClientes = null;
	ConsultasInformes objConsultasInformes = null;
	ImprimirPDF objImprimirPDF = null;
	
	public FormularioInformes() 
	{
		objetoBBDD = new ConexionBD();
		objConsultasCoches = new ConsultasCoches(objetoBBDD);
		objConsultasClientes = new ConsultasClientes(objetoBBDD);
		objConsultasInformes = new ConsultasInformes(objetoBBDD);
		inicio();
	}
	
	public void inicio()
	{
		//PANEL PRINCIPAL
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		
		//ETIQUETAS
		tituloFormulario = new JLabel("INFORMES");
		tituloFormulario.setFont(new Font("arial", Font.ITALIC, 32));
		tituloFormulario.setBounds(10, 10, 200, 50);
		panelPrincipal.add(tituloFormulario);
		
		Clientes = new JLabel("Clientes: ");
		Clientes.setBounds(30, 80, 150, 30);
		panelPrincipal.add(Clientes);
		
		Matricula = new JLabel("Matrícula: ");
		Matricula.setBounds(340, 80, 150, 30);
		panelPrincipal.add(Matricula);
		
		cuadroInforme = new JTextArea();
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		cuadroInforme.setEditable(false);
		cuadroInforme.setBorder(border);
		panelPrincipal.add(cuadroInforme);
		
		scroll = new JScrollPane(cuadroInforme);    
        scroll.setBounds(new Rectangle(70, 130, 600, 280));                                                                     
        panelPrincipal.add(scroll);
		
		//COMBOBOX
		jComboCodigoCliente = new JComboBox<String>();
		jComboCodigoCliente.setBounds(85, 82, 50, 30);
		panelPrincipal.add(jComboCodigoCliente);
		
		jComboMatricula = new JComboBox<String>();
		jComboMatricula.setBounds(400, 82, 100, 30);
		panelPrincipal.add(jComboMatricula);
		
		//BOTONES
		
		botonImprimir = new JButton("IMPRIMIR");
		botonImprimir.setToolTipText("Imprimir informe");
		botonImprimir.setIcon(new ImageIcon(getClass().getResource("/iconos/imprimir.png")));
		botonImprimir.setBounds(190, 16, 130, 35);
		panelPrincipal.add(botonImprimir);
		botonImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String textoImprimir = cuadroInforme.getText();
				objImprimirPDF = new ImprimirPDF();
				objImprimirPDF.realizarImpresion(textoImprimir);		
			}
		});
		
		botonLimpiar = new JButton("LIMPIAR");
		botonLimpiar.setToolTipText("Limpiar informes");
		botonLimpiar.setIcon(new ImageIcon(getClass().getResource("/iconos/limpiar.png")));
		botonLimpiar.setBounds(430, 16, 130, 35);
		panelPrincipal.add(botonLimpiar);
		botonLimpiar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				int respuesta=JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar los informes?",
						"Aviso", JOptionPane.YES_NO_OPTION);
				if(respuesta==JOptionPane.YES_OPTION)
				{
					{cuadroInforme.setText(null);}
					JOptionPane.showMessageDialog(null, "Borrado completado");
				}					
				else
					{JOptionPane.showMessageDialog(null, "Operación cancelada");}			
			}
		});
		
		botonBuscarClientes = new JButton("<html>BUSCAR<br>POR CLIENTE</html>");
		botonBuscarClientes.setIcon(new ImageIcon(getClass().getResource("/iconos/buscar.png")));
		botonBuscarClientes.setBounds(145, 76, 150, 40);
		panelPrincipal.add(botonBuscarClientes);
		botonBuscarClientes.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e)
			{
				buscarPorClientes();				
			}
		});
		
		botonBuscarMatriculas = new JButton("<html>BUSCAR POR<br>MATRÍCULA</html>");
		botonBuscarMatriculas.setIcon(new ImageIcon(getClass().getResource("/iconos/buscar.png")));
		botonBuscarMatriculas.setBounds(510, 76, 150, 40);
		panelPrincipal.add(botonBuscarMatriculas);
		botonBuscarMatriculas.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e)
			{
				buscarPorMatriculas();				
			}
		});
		
	}
	
	////METODOS//////////////////
	
	//CARGAR CLIENTES EXISTENTES AL COMBOBOX
	public void cargarComboBoxClientes()
	{
		jComboCodigoCliente.removeAllItems();
		if(objConsultasClientes.consultarTodo())
		{
			try 
			{
				do{
					jComboCodigoCliente.addItem(objetoBBDD.resultado.getString("codCliente"));							
				}while(objetoBBDD.resultado.next());
			}catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	//CARGAR MATRICULAS EXISTENTES AL COMBOBOX
	public void cargarComboBoxMatriculas()
	{
		jComboMatricula.removeAllItems();
		if(objConsultasCoches.consultarTodo())
		{
			try 
			{
				do{
					jComboMatricula.addItem(objetoBBDD.resultado.getString("matricula"));
				}while(objetoBBDD.resultado.next());
			}catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	//VISUALIZAR LOS DATOS EN EL JTEXTAREA
	public void visualizarDatos() 
	{
		try {
			String aceite = null,filtro = null,frenos = null;
			if(objetoBBDD.resultado.getBoolean("cambAceite")== true)
				{aceite = "SI";}else{aceite = "NO";}
			if(objetoBBDD.resultado.getBoolean("cambFiltro")== true)
			{filtro = "SI";}else{filtro = "NO";}			
			if(objetoBBDD.resultado.getBoolean("revFrenos")== true)
			{frenos = "SI";}else{frenos = "NO";}
			
			String datos = " Nombre: "+objetoBBDD.resultado.getString("nombre")
			+" "+objetoBBDD.resultado.getString("apellidos")+" \n"	
			+" Matricula: "+objetoBBDD.resultado.getString("matricula")+" \n"
			+" Número de revision: "+objetoBBDD.resultado.getString("numRevision")+" \n"
			+" Cambio de aceite: "+aceite+" "+"Cambio del filtro: "+filtro+" "+"Revisión de frenos: "+frenos+" \n"
			+" _________________________________________________ \n\n";
			cuadroInforme.append(datos);
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	//BUSCAR DATOS POR CODIGO DE CLIENTES
	public void buscarPorClientes()
	{
		if(objConsultasInformes.consultarCliente(Integer.parseInt(String.valueOf(jComboCodigoCliente.getSelectedItem())))) 
		{
			try 
			{
				do {
					visualizarDatos();
				}while(objetoBBDD.resultado.next());						
			} catch (SQLException e1) {e1.printStackTrace();}
		}	
	}
	
	//BUSCAR DATOS POR MATRICULA
	public void buscarPorMatriculas()
	{
		if(objConsultasInformes.consultarCoche(String.valueOf(jComboMatricula.getSelectedItem())))
		{
			try 
			{
				do {
					visualizarDatos();
				}while(objetoBBDD.resultado.next());
			}catch (SQLException e1) {e1.printStackTrace();}
		}
	}
}
