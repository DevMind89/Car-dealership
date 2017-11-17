package revisiones;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bbdd.ConexionBD;
import consultasSQL.ConsultasCoches;
import consultasSQL.ConsultasRevisiones;
import navegador.BotonesEdiciones;
import navegador.BotonesFlechas;

public class FormularioRevisiones 
{

	public JPanel panelPrincipal = null;
	
	JLabel tituloFormulario = null;
	JLabel numeroRevision = null;
	JLabel cambioAceite = null;
	JLabel cambioFiltro = null;
	JLabel revisionFrenos = null;
	JLabel matricula = null;
	
	JTextField jtfnumerorevision = null;
	JComboBox<String> jcombocambioaceite = null;
	JComboBox<String> jcombocambiofiltro = null;
	JComboBox<String> jcomborerevisionfrenos = null;
	JComboBox<String> jcombomatricula = null;
	
	JButton cargarDatos = null;
	JButton cargarImagen = null;
	JButton guardarDatos = null;
	JButton modificarDatos = null;
	
	ConexionBD objetoBBDD = null;
	Revisiones objRevisiones = null;
	ConsultasCoches objConsultasCoches = null;
	ConsultasRevisiones objConsultasRevisiones = null;
	BotonesFlechas objBotonesFlechas = null;
	BotonesEdiciones objBotonesEdiciones = null;

	public FormularioRevisiones() 
	{
		objetoBBDD = new ConexionBD();
		objRevisiones = new Revisiones();
		objConsultasRevisiones = new ConsultasRevisiones(objetoBBDD);
		objConsultasCoches = new ConsultasCoches(objetoBBDD);
		objBotonesFlechas = new BotonesFlechas();
		objBotonesEdiciones = new BotonesEdiciones();
		inicio();
	}
	
	public void inicio()
	{
		//PANEL PRINCIPAL
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);		
		
		panelPrincipal.add(objBotonesFlechas.panelBotonesFlechas);//AÑADIR EL PANEL DE BOTONES AL PANEL PRINCIPAL
		panelPrincipal.add(objBotonesEdiciones.panelBotonesEdiciones);
	
		//ETIQUETAS
		tituloFormulario = new JLabel("REVISIONES");
		tituloFormulario.setFont(new Font("arial", Font.ITALIC, 32));
		tituloFormulario.setBounds(10, 10, 200, 50);
		panelPrincipal.add(tituloFormulario);
		
		cargarDatos = new JButton("DATOS");
		cargarDatos.setBounds(210, 18, 125, 30);
		cargarDatos.setIcon(new ImageIcon(getClass().getResource("/iconos/datos.png")));
		cargarDatos.setToolTipText("Cargar datos de coches");
		panelPrincipal.add(cargarDatos);
		cargarDatos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				comprobarConsultarTodo();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
				modificarDatos.setEnabled(false);
			}
		});
		
		numeroRevision = new JLabel("Número de revision: ");
		numeroRevision.setBounds(30, 80, 120, 30);
		panelPrincipal.add(numeroRevision);
		
		cambioAceite = new JLabel("Cambio de aceite: ");
		cambioAceite.setBounds(30, 120, 150, 30);
		panelPrincipal.add(cambioAceite);
		
		cambioFiltro = new JLabel("Cambio de filtro: ");
		cambioFiltro.setBounds(210, 120, 150, 30);
		panelPrincipal.add(cambioFiltro);
		
		revisionFrenos = new JLabel("Revisión de frenos: ");
		revisionFrenos.setBounds(450, 120, 150, 30);
		panelPrincipal.add(revisionFrenos);
		
		matricula = new JLabel("Matrícula: ");
		matricula.setBounds(30, 160, 150, 30);
		panelPrincipal.add(matricula);
		
		//CUADROS DE TEXTOS
		jtfnumerorevision = new JTextField();
		jtfnumerorevision.setBounds(150, 85, 50, 20);
		panelPrincipal.add(jtfnumerorevision);
		
		jcombocambioaceite = new JComboBox<String>();
		jcombocambioaceite.addItem("");	
		jcombocambioaceite.addItem("NO");
		jcombocambioaceite.addItem("SI");		
		jcombocambioaceite.setBounds(135, 125, 50, 20);
		panelPrincipal.add(jcombocambioaceite);
		
		jcombocambiofiltro = new JComboBox<String>();
		jcombocambiofiltro.addItem("");	
		jcombocambiofiltro.addItem("NO");
		jcombocambiofiltro.addItem("SI");		
		jcombocambiofiltro.setBounds(305, 125, 50, 20);
		panelPrincipal.add(jcombocambiofiltro);
		
		jcomborerevisionfrenos = new JComboBox<String>();
		jcomborerevisionfrenos.addItem("");
		jcomborerevisionfrenos.addItem("NO");
		jcomborerevisionfrenos.addItem("SI");		
		jcomborerevisionfrenos.setBounds(560, 125, 50, 20);
		panelPrincipal.add(jcomborerevisionfrenos);
		
		jcombomatricula = new JComboBox<String>();
		jcombomatricula.setBounds(100, 162, 120, 25);
		panelPrincipal.add(jcombomatricula);
		
		//BOTONES EDICIONES
		objBotonesEdiciones.anadir.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent e) 
			{
				limpiarTextField();
				desbloquearTextField();
				guardarDatos.setEnabled(true);
				modificarDatos.setEnabled(false);
			}
		});
		
		objBotonesEdiciones.buscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String numeroRevision=(JOptionPane.showInputDialog("Introduzca número de revisión a buscar"));
				if(numeroRevision!=null)
				{
					comprobarNumeroRevisionExistente(numeroRevision);		
				}
				guardarDatos.setEnabled(false);
			}
		});
		
		objBotonesEdiciones.editar.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e) 
			{
				confirmarEditarNumeroRevision();
			}
		});
		
		objBotonesEdiciones.borrar.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e) 
			{			
				confirmarBorrarNumeroRevision();			
			}
		});
		
		//BOTONES FLECHAS
		objBotonesFlechas.primero.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				comprobarPrimeraRevision();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}
		});			
				
		objBotonesFlechas.siguiente.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e)
			{
				comprobarRevisionSiguiente();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}				
		});			
		
		objBotonesFlechas.anterior.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e)
			{
				comprobarRevisionAnterior();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}
		});		
		
		objBotonesFlechas.ultimo.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent e) 
			{									
				comprobarUltimaRevision();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}			
		});
		
		guardarDatos = new JButton("GUARDAR");
		guardarDatos.setBounds(420, 380, 130, 30);
		guardarDatos.setIcon(new ImageIcon(getClass().getResource("/iconos/guardar.png")));
		guardarDatos.setToolTipText("Guardar datos");
		panelPrincipal.add(guardarDatos);
		guardarDatos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				anadirRevisiones();
			}
		});
		
		modificarDatos = new JButton("MODIFICAR");
		modificarDatos.setBounds(570, 380, 140, 30);
		modificarDatos.setIcon(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		modificarDatos.setToolTipText("Modificar datos");
		modificarDatos.setEnabled(false);
		panelPrincipal.add(modificarDatos);
		modificarDatos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				modificarRevisiones();
			}
		});
	}
	
	////METODOS
	
	//VISUALIZAR LOS DATOS EN LOS TEXTFIELD
	public void visualizarDatos()
	{
		try{
			jtfnumerorevision.setText(objetoBBDD.resultado.getString("numRevision"));
			if(objetoBBDD.resultado.getInt("cambAceite")==1)
				jcombocambioaceite.setSelectedItem("SI");
			else 
				jcombocambioaceite.setSelectedItem("NO");
			
			if(objetoBBDD.resultado.getInt("cambFiltro")==1) 
				jcombocambiofiltro.setSelectedItem("SI");
			else 
				jcombocambiofiltro.setSelectedItem("NO");
			
			if(objetoBBDD.resultado.getInt("revFrenos")==1) 
				jcomborerevisionfrenos.setSelectedItem("SI");
			else 
				jcomborerevisionfrenos.setSelectedItem("NO");
			
			jcombomatricula.setSelectedItem(objetoBBDD.resultado.getString("matricula"));		
		}catch (SQLException e){e.printStackTrace();}		
	}
		
	//ASIGNAR DATOS
	public void asignarDatos()
	{	
		objRevisiones.setNumeroRevision(Integer.parseInt(jtfnumerorevision.getText()));
		
		if(jcombocambioaceite.getSelectedItem()=="SI")
			{objRevisiones.setCambioAceite(true);}
		else 
			{objRevisiones.setCambioAceite(false);}
		
		if(jcombocambiofiltro.getSelectedItem()=="SI")
			{objRevisiones.setCambioFiltro(true);}
		else 
			{objRevisiones.setCambioFiltro(false);}
		
		if(jcomborerevisionfrenos.getSelectedItem()=="SI")
			{objRevisiones.setRevisionFrenos(true);}
		else 
			{objRevisiones.setRevisionFrenos(false);}
			
		objRevisiones.setMatricula(String.valueOf(jcombomatricula.getSelectedItem()));		
	}
		
	//CARGAR MATRICULAS EXISTENTES AL COMBOBOX
	public void cargarComboBox()
	{
		jcombomatricula.removeAllItems();
		if(objConsultasCoches.consultarTodo())
		{
			try 
			{
				do{
					jcombomatricula.addItem(objetoBBDD.resultado.getString("matricula"));
				}while(objetoBBDD.resultado.next());
			}catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	//LIMPIAR DATOS DE TEXTFIELD
	public void limpiarTextField()
	{
		jtfnumerorevision.setText("");	
		jcombocambioaceite.setSelectedItem("");
		jcombocambiofiltro.setSelectedItem("");
		jcomborerevisionfrenos.setSelectedItem("");
	}
	
	//COMPROBAR TEXTFIELD VACIOS
	public boolean comprobarTextFildVacios()
	{
		if(jtfnumerorevision.getText().equals("")) {return false;}
		else if(jcombocambioaceite.getSelectedItem()==""){return false;}
		else if(jcombocambiofiltro.getSelectedItem()==""){return false;}
		else if(jcomborerevisionfrenos.getSelectedItem()==""){return false;}
		return true;			
	}
	
	//DESBLOQUEAR TEXTFIELD
	public void desbloquearTextField()
	{
		jtfnumerorevision.setEnabled(true);
		jcombocambioaceite.setEnabled(true);
		jcombocambiofiltro.setEnabled(true);
		jcomborerevisionfrenos.setEnabled(true);
		jcombomatricula.setEnabled(true);
	}
	
	//BLOQUEAR TEXTFIELD
	public void bloquearTextField()
	{
		jtfnumerorevision.setEnabled(false);
		jcombocambioaceite.setEnabled(false);
		jcombocambiofiltro.setEnabled(false);
		jcomborerevisionfrenos.setEnabled(false);
		jcombomatricula.setEnabled(false);
	}

	//HABILITAR COLOR A LETRA DE UN TEXTFIELD BLOQUEADO
	public void colorTextFieldBloqueado()
	{
		jtfnumerorevision.setDisabledTextColor(Color.black);
		UIManager.put("ComboBox.disabledBackground", new Color(240,240,240));
		UIManager.put("ComboBox.disabledForeground", Color.BLACK);
	}
	
	////COSULTAR TODA LA BASE DE DATOS
	public void comprobarConsultarTodo()
	{
		if (objConsultasRevisiones.consultarTodo())
		{
			visualizarDatos();	
		}						
		else 
		{
			JOptionPane.showMessageDialog(null, "La base de datos está vacía,Ingrese datos");
		}	
	}
	
	//IR A LA PRIMERA REVISION
	public void comprobarPrimeraRevision()
	{
		if(jtfnumerorevision.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasRevisiones.consultarPrimeraRevision())
			{
				visualizarDatos();	
			}						
			else
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en la primera revisión");
			}
		}		
	}
	
	//RETROCEDER A LA REVISION ANTERIOR
	public void comprobarRevisionAnterior()
	{
		if(jtfnumerorevision.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasRevisiones.consultarRevisionAnterior())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en la primera revisión");
			}	
		}			
	}
	
	//PASAR A LA SIGUIENTE REVISION 
 	public void comprobarRevisionSiguiente()
	{
		if(jtfnumerorevision.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasRevisiones.consultarSiguienteRevision())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en la última revisión");
			}	
		}			
	}
 			
	//IR A LA ULTIMA REVISION
	public void comprobarUltimaRevision()
	{
		if(jtfnumerorevision.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasRevisiones.consultarUltimaRevision())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en la última revisión");
			}		
		}			
	}
 		
	//CONSULTAR REVISION EXISTENTE
	public void comprobarNumeroRevisionExistente(String numeroRevision)
	{	
		if(objConsultasRevisiones.buscarNumeroRevisiones(Integer.parseInt(numeroRevision)))
		{
			visualizarDatos();
			posicionarConsulta(Integer.parseInt(numeroRevision));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Ese número de revisión no existe","Error",JOptionPane.ERROR_MESSAGE);
			bloquearTextField();
			colorTextFieldBloqueado();
			modificarDatos.setEnabled(false);
			posicionarConsulta(1);
			visualizarDatos();
		}		
	}
	
	//POSICIONAR CONSULTA
	public void posicionarConsulta(int numeroRevision)
	{
		objConsultasRevisiones.consultarTodo();
		try {
			while(objetoBBDD.resultado.getInt("numRevision")!=numeroRevision)
			{
				objetoBBDD.resultado.next();
			}
		} catch (SQLException e){e.printStackTrace();}
	}
	
	//AÑADIR REVISIONES
	public void anadirRevisiones()
	{
		if(!comprobarTextFildVacios())
		{
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}
		else
		{
			asignarDatos();
			objConsultasRevisiones.anadirRevisiones(objRevisiones);
			limpiarTextField();
			posicionarConsulta(1);
			visualizarDatos();
		}
	}
	
	//MODIFICAR DATOS DE REVISIONES
	public void modificarRevisiones()
	{
		if(!comprobarTextFildVacios())
		{
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}
		else
		{
			asignarDatos();
			objConsultasRevisiones.modificarRevisiones(objRevisiones);
			limpiarTextField();
			posicionarConsulta(1);
			visualizarDatos();
			JOptionPane.showMessageDialog(null, "Datos de la revisión modificados correctamente");
			modificarDatos.setEnabled(false);
		}
	}
	//CONFIRMAR PARA EDITAR DATOS DE LA REVISION
	public void confirmarEditarNumeroRevision()
	{
		if(jtfnumerorevision.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Elija primero una revisión para modificar sus datos");
		}
		else
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "¿Seguro que quiere modificar esta revisión?",
					"Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			if(respuesta==JOptionPane.YES_OPTION)
			{
				desbloquearTextField();
				guardarDatos.setEnabled(false);
				modificarDatos.setEnabled(true);
				jtfnumerorevision.setEnabled(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Operación cancelada");
				modificarDatos.setEnabled(false);
			}
		}		
	}
	
	//CONFIRMAR PARA BORRAR DATOS DE COCHES
	public void confirmarBorrarNumeroRevision()
	{
		if(jtfnumerorevision.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Elija primero una revisión para modificar sus datos");
		}
		else
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar esta revisión?",
					"Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			try
			{
				if(respuesta==JOptionPane.YES_OPTION)
				{
					objConsultasRevisiones.borrarRevisiones(Integer.parseInt(objetoBBDD.resultado.getString("numRevision")));
					limpiarTextField();
					JOptionPane.showMessageDialog(null, "Revisión borrada correctamente");
					posicionarConsulta(1);
					visualizarDatos();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Operación cancelada");
				}				
			}catch (NumberFormatException e) {e.printStackTrace();}catch (SQLException e) {e.printStackTrace();}			
		}		
	}
}	
