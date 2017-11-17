package clientes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import bbdd.ConexionBD;
import consultasSQL.ConsultasClientes;
import navegador.BotonesEdiciones;
import navegador.BotonesFlechas;

public class FormularioClientes 
{
	
	public JPanel panelPrincipal = null;
	
	JLabel tituloFormulario = null;
	JLabel codigoCliente = null;
	JLabel nombre = null;
	JLabel apellidos = null;
	JLabel direccion = null;
	JLabel poblacion = null;
	JLabel codigoPostal = null;
	JLabel provincia = null;
	JLabel telefono = null;
	JLabel fechaNacimiento = null;
	
	JTextField jtfcodigoCliente = null;
	JTextField jtfnombre = null;
	JTextField jtfapellidos = null;
	JTextField jtfdireccion = null;
	JTextField jtfpoblacion = null;
	JTextField jtfcodigoPostal = null;
	JTextField jtfprovincia = null;
	JTextField jtftelefono = null;
	JTextField jtffechaNacimiento = null;
	
	JButton cargarDatos = null;
	JButton guardarDatos = null;
	JButton modificarDatos = null;
	
	ConexionBD objetoBBDD = null;
	ConsultasClientes objConsultasClientes = null;
	Clientes objClientes = null;
	BotonesFlechas objBotonesFlechas = null;
	BotonesEdiciones objBotonesEdiciones = null;
	
	public FormularioClientes()
	{		
		objetoBBDD = new ConexionBD();
		objConsultasClientes = new ConsultasClientes(objetoBBDD);
		objClientes = new Clientes();
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
		tituloFormulario = new JLabel("CLIENTES");
		tituloFormulario.setFont(new Font("arial", Font.ITALIC, 32));
		tituloFormulario.setBounds(10, 10, 200, 50);
		panelPrincipal.add(tituloFormulario);
		
		cargarDatos = new JButton("DATOS");
		cargarDatos.setBounds(195, 18, 125, 30);
		cargarDatos.setIcon(new ImageIcon(getClass().getResource("/iconos/datos.png")));
		cargarDatos.setToolTipText("Cargar datos de clientes");
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
		
		codigoCliente = new JLabel("Código del cliente: ");
		codigoCliente.setBounds(30, 80, 150, 30);
		panelPrincipal.add(codigoCliente);
		
		nombre = new JLabel("Nombre: ");
		nombre.setBounds(30, 120, 150, 30);
		panelPrincipal.add(nombre);
		
		apellidos = new JLabel("Apellidos: ");
		apellidos.setBounds(210, 120, 150, 30);
		panelPrincipal.add(apellidos);
		
		fechaNacimiento = new JLabel("Fecha de nacimiento: ");
		fechaNacimiento.setBounds(450, 120, 150, 30);
		panelPrincipal.add(fechaNacimiento);
		
		direccion = new JLabel("Dirección: ");
		direccion.setBounds(30, 160, 150, 30);
		panelPrincipal.add(direccion);
		
		poblacion = new JLabel("Población: ");
		poblacion.setBounds(30, 200, 150, 30);
		panelPrincipal.add(poblacion);
		
		codigoPostal = new JLabel("Código Postal: ");
		codigoPostal.setBounds(230, 200, 150, 30);
		panelPrincipal.add(codigoPostal);
		
		provincia = new JLabel("Provincia: ");
		provincia.setBounds(30, 240, 150, 30);
		panelPrincipal.add(provincia);
		
		telefono = new JLabel("Teléfono: ");
		telefono.setBounds(30, 280, 150, 30);
		panelPrincipal.add(telefono);
		
		//CUADROS DE TEXTOS
		jtfcodigoCliente = new JTextField();
		jtfcodigoCliente.setBounds(140, 85, 30, 20);
		panelPrincipal.add(jtfcodigoCliente);
		
		jtfnombre = new JTextField();
		jtfnombre.setBounds(85, 125, 100, 20);
		panelPrincipal.add(jtfnombre);
		
		jtfapellidos = new JTextField();
		jtfapellidos.setBounds(275, 125, 150, 20);
		panelPrincipal.add(jtfapellidos);
		
		jtffechaNacimiento = new JTextField();
		jtffechaNacimiento.setBounds(580, 125, 100, 20);
		panelPrincipal.add(jtffechaNacimiento);
		
		jtfdireccion = new JTextField();
		jtfdireccion.setBounds(95, 165, 150, 20);
		panelPrincipal.add(jtfdireccion);
		
		jtfpoblacion = new JTextField();
		jtfpoblacion.setBounds(95, 205, 100, 20);
		panelPrincipal.add(jtfpoblacion);
		
		jtfcodigoPostal = new JTextField();
		jtfcodigoPostal.setBounds(320, 205, 50, 20);
		panelPrincipal.add(jtfcodigoPostal);
		
		jtfprovincia = new JTextField();
		jtfprovincia.setBounds(95, 245, 150, 20);
		panelPrincipal.add(jtfprovincia);
		
		jtftelefono = new JTextField();
		jtftelefono.setBounds(90, 285, 80, 20);
		panelPrincipal.add(jtftelefono);
		
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
				String buscarClientes = (JOptionPane.showInputDialog("Introduzca el código del cliente a buscar"));
				if(buscarClientes != null)
				{
					comprobarClienteExistente(buscarClientes);		
				}
				guardarDatos.setEnabled(false);
			}
		});
		
		objBotonesEdiciones.editar.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e) 
			{
				confirmarEditarCliente();
			}
		});
		
		objBotonesEdiciones.borrar.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e) 
			{			
				confirmarBorrarCliente();			
			}
		});
		
		//BOTONES FLECHAS
		objBotonesFlechas.primero.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				comprobarClientePrimero();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}
		});			
		
		objBotonesFlechas.siguiente.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e)
			{
				comprobarClienteSiguiente();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}				
		});			
		
		objBotonesFlechas.anterior.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e)
			{
				comprobarClienteAnterior();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}
		});		
		
		objBotonesFlechas.ultimo.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent e) 
			{									
				comprobarClienteUltimo();
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
				anadirClientes();		
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
				modificarClientes();
			}
		});
	}
	
	////METODOS//////////////////
	
	//VISUALIZAR LOS DATOS EN LOS TEXTFIELD
	public void visualizarDatos()
	{		
		try {
			jtfcodigoCliente.setText(objetoBBDD.resultado.getString("codCliente"));
			jtfnombre.setText(objetoBBDD.resultado.getString("nombre"));
			jtfapellidos.setText(objetoBBDD.resultado.getString("apellidos"));
			jtffechaNacimiento.setText(objetoBBDD.resultado.getString("fechaNac"));
			jtfdireccion.setText(objetoBBDD.resultado.getString("direccion"));
			jtfpoblacion.setText(objetoBBDD.resultado.getString("poblacion"));
			jtfcodigoPostal.setText(objetoBBDD.resultado.getString("codPostal"));
			jtfprovincia.setText(objetoBBDD.resultado.getString("provincia"));
			jtftelefono.setText(objetoBBDD.resultado.getString("telefono"));
		} catch (SQLException e){e.printStackTrace();}		
	}
	
	//ASIGNAR DATOS
	public void asignarDatos()
	{
		objClientes.setCodigoCliente(Integer.parseInt(jtfcodigoCliente.getText()));
		objClientes.setNombre(jtfnombre.getText());
		objClientes.setApellidos(jtfapellidos.getText());
		objClientes.setDireccion(jtfdireccion.getText());
		objClientes.setPoblacion(jtfpoblacion.getText());
		objClientes.setCodigoPostal(jtfcodigoPostal.getText());
		objClientes.setProvincia(jtfprovincia.getText());
		objClientes.setTelefono(jtftelefono.getText());
		objClientes.setFechaNacimiento(jtffechaNacimiento.getText());
	}
	
	//LIMPIAR DATOS DE TEXTFIELD
	public void limpiarTextField()
	{
		jtfcodigoCliente.setText("");
		jtfnombre.setText("");
		jtfapellidos.setText("");
		jtffechaNacimiento.setText("");
		jtfdireccion.setText("");
		jtfpoblacion.setText("");
		jtfcodigoPostal.setText("");
		jtfprovincia.setText("");
		jtftelefono.setText("");
	}
	
	//COMPROBAR TEXTFIELD VACIOS
	public boolean comprobarTextFildVacios()
	{
		if(jtfcodigoCliente.getText().equals("")) {return false;}
		else if(jtfnombre.getText().equals("")) {return false;}
		else if(jtfapellidos.getText().equals("")) {return false;}
		else if(jtfdireccion.getText().equals("")) {return false;}
		else if(jtfpoblacion.getText().equals("")) {return false;}
		else if(jtfcodigoPostal.getText().equals("")) {return false;}
		else if(jtfprovincia.getText().equals("")) {return false;}
		else if(jtftelefono.getText().equals("")) {return false;}
		else if(jtffechaNacimiento.getText().equals("")) {return false;}
		return true;			
	}
	
	//DESBLOQUEAR TEXTFIELD
	public void desbloquearTextField()
	{
		jtfcodigoCliente.setEnabled(true);
		jtfnombre.setEnabled(true);
		jtfapellidos.setEnabled(true);
		jtffechaNacimiento.setEnabled(true);
		jtfdireccion.setEnabled(true);
		jtfpoblacion.setEnabled(true);
		jtfcodigoPostal.setEnabled(true);
		jtfprovincia.setEnabled(true);
		jtftelefono.setEnabled(true);		
	}
	
	//BLOQUEAR TEXTFIELD
	public void bloquearTextField()
	{
		jtfcodigoCliente.setEnabled(false);
		jtfnombre.setEnabled(false);
		jtfapellidos.setEnabled(false);
		jtffechaNacimiento.setEnabled(false);
		jtfdireccion.setEnabled(false);
		jtfpoblacion.setEnabled(false);
		jtfcodigoPostal.setEnabled(false);
		jtfprovincia.setEnabled(false);
		jtftelefono.setEnabled(false);		
	}
	
	//HABILITAR COLOR A LETRA DE UN TEXTFIELD BLOQUEADO
	public void colorTextFieldBloqueado()
	{
		jtfcodigoCliente.setDisabledTextColor(Color.black);
		jtfnombre.setDisabledTextColor(Color.black);
		jtfapellidos.setDisabledTextColor(Color.black);
		jtffechaNacimiento.setDisabledTextColor(Color.black);
		jtfdireccion.setDisabledTextColor(Color.black);
		jtfpoblacion.setDisabledTextColor(Color.black);
		jtfcodigoPostal.setDisabledTextColor(Color.black);
		jtfprovincia.setDisabledTextColor(Color.black);
		jtftelefono.setDisabledTextColor(Color.black);
	}
	
	//COSULTAR TODA LA BASE DE DATOS
	public void comprobarConsultarTodo()
	{
		if (objConsultasClientes.consultarTodo())
		{
			visualizarDatos();	
		}						
		else 
		{
			JOptionPane.showMessageDialog(null, "La base de datos está vacía,Ingrese datos");
		}	
	}
	
	//IR AL PRIMER CLIENTE
	public void comprobarClientePrimero()
	{
		if(jtfcodigoCliente.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasClientes.consultarClientePrimero())
			{
				visualizarDatos();	
			}						
			else
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el primer cliente");
			}
		}		
	}
	
	//RETROCEDER AL CLIENTE ANTERIOR
	public void comprobarClienteAnterior()
	{
		if(jtfcodigoCliente.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasClientes.consultarClienteAnterior())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el primer cliente");
			}	
		}			
	}
	
	//PASAR AL SIGUIENTE CLIENTE
	public void comprobarClienteSiguiente()
	{
		if(jtfcodigoCliente.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasClientes.consultarClienteSiguiente())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el último cliente");
			}	
		}			
	}
	
	//IR AL ULTIMO CLIENTE	
	public void comprobarClienteUltimo()
	{
		if(jtfcodigoCliente.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasClientes.consultarClienteUltimo())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el último cliente");
			}		
		}			
	}
	
	//POSICIONAR CONSULTA
	public void posicionarConsulta(int buscarClientes)
	{
		objConsultasClientes.consultarTodo();
		try {			
			while(objetoBBDD.resultado.getInt("codCliente") != buscarClientes )
			{
				objetoBBDD.resultado.next();
			}
		} catch (SQLException e){e.printStackTrace();}
	}
	
	//AÑADIR CLIENTES
	public void anadirClientes()
	{
		if(!comprobarTextFildVacios())
		{
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}
		else
		{
			asignarDatos();
			objConsultasClientes.anadirClientes(objClientes);
			limpiarTextField();
			posicionarConsulta(1);
			visualizarDatos();
			guardarDatos.setEnabled(false);
		}
	}
	
	//CONSULTAR CLIENTE EXISTENTE
	public void comprobarClienteExistente(String buscarClientes)
	{	
		if(objConsultasClientes.buscarClientes(Integer.parseInt(buscarClientes)))
		{
			visualizarDatos();
			posicionarConsulta(Integer.parseInt(buscarClientes));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Ese cliente no existe","Error",JOptionPane.ERROR_MESSAGE);
			posicionarConsulta(1);
			visualizarDatos();
		}		
	}
	
	//MODIFICAR DATOS DE CLIENTES
	public void modificarClientes()
	{
		if(!comprobarTextFildVacios())
		{
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}
		else
		{
			asignarDatos();
			objConsultasClientes.modificarCliente(objClientes);
			limpiarTextField();
			posicionarConsulta(1);
			visualizarDatos();
			JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
			modificarDatos.setEnabled(false);
		}
	}
	//CONFIRMAR PARA EDITAR DATOS DE CLIENTES
	public void confirmarEditarCliente()
	{
		if(jtfcodigoCliente.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Elija primero un cliente para modificar sus datos");
		}
		else
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "¿Seguro que quiere modificar este cliente?",
					"Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			if(respuesta==JOptionPane.YES_OPTION)
			{
				desbloquearTextField();
				guardarDatos.setEnabled(false);
				modificarDatos.setEnabled(true);
				jtfcodigoCliente.setEnabled(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Operación cancelada");
				modificarDatos.setEnabled(false);
			}
		}		
	}
	
	//CONFIRMAR PARA BORRAR DATOS DE CLIENTES
	public void confirmarBorrarCliente()
	{
		if(jtfcodigoCliente.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Elija primero un cliente para modificar sus datos");
		}
		else
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar este cliente?",
					"Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			try
			{
				if(respuesta==JOptionPane.YES_OPTION)
				{
					objConsultasClientes.borrarCliente(Integer.parseInt(objetoBBDD.resultado.getString("codCliente")));
					limpiarTextField();
					JOptionPane.showMessageDialog(null, "Cliente borrado correctamente");
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
