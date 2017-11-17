package coches;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import bbdd.ConexionBD;
import consultasSQL.ConsultasClientes;
import consultasSQL.ConsultasCoches;
import navegador.BotonesEdiciones;
import navegador.BotonesFlechas;

public class FormularioCoches
{
	public JPanel panelPrincipal = null;
	
	JLabel tituloFormulario = null;
	JLabel matricula = null;
	JLabel marca = null;
	JLabel modelo = null;
	JLabel color = null;
	JLabel precio = null;
	JLabel codigoCliente = null;
	JLabel cargarfotografia = null;
	
	JTextField jtfmatricula = null;
	JTextField jtfmarca = null;
	JTextField jtfmodelo = null;
	JTextField jtfcolor = null;
	JTextField jtfprecio = null;
	JComboBox<String> jCombocodigocliente = null;
	
	JButton cargarDatos = null;
	JButton cargarImagen = null;
	JButton guardarDatos = null;
	JButton modificarDatos = null;
	
	JFileChooser fileChooserFoto = null;
	
	ConexionBD objetoBBDD = null;
	ConsultasCoches objConsultasCoches = null;
	ConsultasClientes objConsultasClientes = null;
	Coches objCoches = null;
	BotonesFlechas objBotonesFlechas = null;
	BotonesEdiciones objBotonesEdiciones = null;
	
	public FormularioCoches()
	{
		objetoBBDD = new ConexionBD();
		objConsultasCoches = new ConsultasCoches(objetoBBDD);
		objConsultasClientes = new ConsultasClientes(objetoBBDD);
		objCoches = new Coches();
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
		tituloFormulario = new JLabel("COCHES");
		tituloFormulario.setFont(new Font("arial", Font.ITALIC, 32));
		tituloFormulario.setBounds(10, 10, 200, 50);
		panelPrincipal.add(tituloFormulario);
		
		cargarDatos = new JButton("DATOS");
		cargarDatos.setBounds(195, 18, 125, 30);
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
				cargarImagen.setEnabled(false);
			}
		});
		
		matricula = new JLabel("Matrícula: ");
		matricula.setBounds(30, 80, 150, 30);
		panelPrincipal.add(matricula);
		
		marca = new JLabel("Marca: ");
		marca.setBounds(30, 120, 150, 30);
		panelPrincipal.add(marca);
		
		modelo = new JLabel("Modelo: ");
		modelo.setBounds(210, 120, 150, 30);
		panelPrincipal.add(modelo);
		
		color = new JLabel("Color: ");
		color.setBounds(450, 120, 150, 30);
		panelPrincipal.add(color);
		
		precio = new JLabel("Precio: ");
		precio.setBounds(30, 160, 150, 30);
		panelPrincipal.add(precio);
		
		codigoCliente = new JLabel("Código cliente: ");
		codigoCliente.setBounds(30, 200, 150, 30);
		panelPrincipal.add(codigoCliente);
		
		cargarfotografia = new JLabel();
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		cargarfotografia.setBorder(border);
		cargarfotografia.setBounds(400, 160, 300, 200);
		panelPrincipal.add(cargarfotografia);
		
		
		//CUADROS DE TEXTOS
		jtfmatricula = new JTextField();
		jtfmatricula.setBounds(90, 85, 150, 20);
		panelPrincipal.add(jtfmatricula);
		
		jtfmarca = new JTextField();
		jtfmarca.setBounds(75, 125, 120, 20);
		panelPrincipal.add(jtfmarca);
		
		jtfmodelo = new JTextField();
		jtfmodelo.setBounds(265, 125, 170, 20);
		panelPrincipal.add(jtfmodelo);
		
		jtfcolor = new JTextField();
		jtfcolor.setBounds(490, 125, 100, 20);
		panelPrincipal.add(jtfcolor);
		
		jtfprecio = new JTextField();
		jtfprecio.setBounds(80, 165, 90, 20);
		panelPrincipal.add(jtfprecio);
		
		jCombocodigocliente = new JComboBox<String>();
		jCombocodigocliente.setBounds(120, 200, 80, 25);
		panelPrincipal.add(jCombocodigocliente);
	
		//BOTONES EDICIONES
		objBotonesEdiciones.anadir.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent e) 
			{
				limpiarTextField();
				desbloquearTextField();
				cargarComboBox();
				guardarDatos.setEnabled(true);
				modificarDatos.setEnabled(false);
				cargarImagen.setEnabled(true);
				cargarfotografia.setIcon(null);
			}
		});
		
		objBotonesEdiciones.buscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String buscarMatricula=(JOptionPane.showInputDialog("Introduzca matrícula a buscar"));
				if(buscarMatricula != null)
				{
					comprobarMatriculaExistente(buscarMatricula);		
				}
				guardarDatos.setEnabled(false);
			}
		});
		
		objBotonesEdiciones.editar.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e) 
			{
				confirmarEditarCoche();
			}
		});
		
		objBotonesEdiciones.borrar.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e) 
			{			
				confirmarBorrarCoche();			
			}
		});
		
		//BOTONES FLECHAS
		objBotonesFlechas.primero.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				comprobarPrimerCoche();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}
		});			
				
		objBotonesFlechas.siguiente.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e)
			{
				comprobarCocheSiguiente();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}				
		});			
		
		objBotonesFlechas.anterior.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e)
			{
				comprobarCocheAnterior();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}
		});		
		
		objBotonesFlechas.ultimo.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent e) 
			{									
				comprobarUltimoCoche();
				bloquearTextField();
				colorTextFieldBloqueado();
				guardarDatos.setEnabled(false);
			}			
		});
		
		cargarImagen = new JButton("<html>CARGAR<br>IMAGEN</html>");
		cargarImagen.setBounds(278, 320, 120, 40);
		cargarImagen.setToolTipText("Cargar imagen");
		cargarImagen.setIcon(new ImageIcon(getClass().getResource("/iconos/cargar.png")));
		panelPrincipal.add(cargarImagen);
		cargarImagen.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e)
			{
				cargarImagenCoche();
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
				anadirCoches();
				
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
				modificarCoches();
			}
		});
	}
	
	////METODOS//////////////////
	
	//VISUALIZAR LOS DATOS EN LOS TEXTFIELD
	public void visualizarDatos()
	{
		jCombocodigocliente.removeAllItems();		
		try{
			jtfmatricula.setText(objetoBBDD.resultado.getString("matricula"));
			jtfmarca.setText(objetoBBDD.resultado.getString("marca"));
			jtfmodelo.setText(objetoBBDD.resultado.getString("modelo"));
			jtfcolor.setText(objetoBBDD.resultado.getString("color"));
			jtfprecio.setText(objetoBBDD.resultado.getString("precio"));
			jCombocodigocliente.addItem(objetoBBDD.resultado.getString("codCliente"));					
			ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenescoches/"+objetoBBDD.resultado.getString("fotografia")));
			ImageIcon ImagenEscalada = new ImageIcon(Imagen.getImage()
					.getScaledInstance(cargarfotografia.getWidth(), cargarfotografia.getHeight(),Image.SCALE_DEFAULT));
			cargarfotografia.setIcon(ImagenEscalada);						
		}catch (SQLException e){e.printStackTrace();}					
	}
	
	//ASIGNAR DATOS
	public void asignarDatos()
	{
		objCoches.setMatricula(jtfmatricula.getText().toUpperCase());
		objCoches.setMarca(jtfmarca.getText());
		objCoches.setModelo(jtfmodelo.getText());
		objCoches.setColor(jtfcolor.getText());
		objCoches.setPrecio(Float.parseFloat((jtfprecio.getText())));
		objCoches.setCodigoCliente(String.valueOf(jCombocodigocliente.getSelectedItem()));
	}
	
	//RUTA PARA CARGAR IMAGENES DE COCHES
	public void cargarImagenCoche()
	{
		fileChooserFoto = new JFileChooser("src/imagenescoches");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF Images","jpg","png","gif");
		fileChooserFoto.setFileFilter(filtro);
		fileChooserFoto.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int seleccion = fileChooserFoto.showOpenDialog(panelPrincipal);
		if(seleccion == JFileChooser.APPROVE_OPTION)
		{
			ImageIcon Imagen = new ImageIcon(fileChooserFoto.getSelectedFile().getPath());
			ImageIcon ImagenEscalada = new ImageIcon(Imagen.getImage()
					.getScaledInstance(cargarfotografia.getWidth(), cargarfotografia.getHeight(),Image.SCALE_DEFAULT));
			cargarfotografia.setIcon(ImagenEscalada);
			String nombre = fileChooserFoto.getSelectedFile().getName();
			objCoches.setFotografia(nombre);
		}
	}
	
	//CARGAR CLIENTES EXISTENTES AL COMBOBOX
	public void cargarComboBox()
	{
		jCombocodigocliente.removeAllItems();
		if(objConsultasClientes.consultarTodo())
		{
			try 
			{
				do{
					jCombocodigocliente.addItem(objetoBBDD.resultado.getString("codCliente"));
				}while(objetoBBDD.resultado.next());
			}catch (SQLException e) {e.printStackTrace();}
		}
	}	
	
	//LIMPIAR DATOS DE TEXTFIELD
	public void limpiarTextField()
	{
		jtfmatricula.setText("");
		jtfmarca.setText("");
		jtfmodelo.setText("");
		jtfcolor.setText("");
		jtfprecio.setText("");		
	}
	
	//COMPROBAR TEXTFIELD VACIOS
	public boolean comprobarTextFildVacios()
	{
		if(jtfmatricula.getText().equals("")) {return false;}
		else if(jtfmarca.getText().equals("")) {return false;}
		else if(jtfmodelo.getText().equals("")) {return false;}
		else if(jtfcolor.getText().equals("")) {return false;}
		else if(jtfprecio.getText().equals("")) {return false;}
		else if(jCombocodigocliente.getItemCount()==0){return false;}
		return true;			
	}
	
	//DESBLOQUEAR TEXTFIELD
	public void desbloquearTextField()
	{
		jtfmatricula.setEnabled(true);
		jtfmarca.setEnabled(true);
		jtfmodelo.setEnabled(true);
		jtfcolor.setEnabled(true);
		jtfprecio.setEnabled(true);
		jCombocodigocliente.setEnabled(true);
	}
	
	//BLOQUEAR TEXTFIELD
	public void bloquearTextField()
	{
		jtfmatricula.setEnabled(false);
		jtfmarca.setEnabled(false);
		jtfmodelo.setEnabled(false);
		jtfcolor.setEnabled(false);
		jtfprecio.setEnabled(false);
		jCombocodigocliente.setEnabled(false);	
	}

	//HABILITAR COLOR A LETRA DE UN TEXTFIELD BLOQUEADO
	public void colorTextFieldBloqueado()
	{
		jtfmatricula.setDisabledTextColor(Color.black);
		jtfmarca.setDisabledTextColor(Color.black);
		jtfmodelo.setDisabledTextColor(Color.black);
		jtfcolor.setDisabledTextColor(Color.black);
		jtfprecio.setDisabledTextColor(Color.black);
		UIManager.put("ComboBox.disabledBackground", new Color(240,240,240));
		UIManager.put("ComboBox.disabledForeground", Color.BLACK);
	}
	
	////COSULTAR TODA LA BASE DE DATOS
	public void comprobarConsultarTodo()
	{
		if (objConsultasCoches.consultarTodo())
		{
			visualizarDatos();	
		}						
		else 
		{
			JOptionPane.showMessageDialog(null, "La base de datos está vacía,Ingrese datos");
		}	
	}
	
	//IR AL PRIMER COCHE
	public void comprobarPrimerCoche()
	{
		if(jtfmatricula.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasCoches.consultarPrimerCoche())
			{
				visualizarDatos();	
			}						
			else
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el primer coche");
			}
		}		
	}
	
 	//RETROCEDER AL COCHE ANTERIOR
	public void comprobarCocheAnterior()
	{
		if(jtfmatricula.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasCoches.consultarCocheAnterior())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el primer coche");
			}	
		}			
	}
	
	//PASAR AL COCHE SIGUIENTE
 	public void comprobarCocheSiguiente()
	{
		if(jtfmatricula.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasCoches.consultarCocheSiguiente())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el último coche");
			}	
		}			
	}
 			
	//IR AL ULTIMO COCHE	
	public void comprobarUltimoCoche()
	{
		if(jtfmatricula.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Debe cargar datos");
		}
		else
		{
			if (objConsultasCoches.consultarUltimoCoche())
			{
				visualizarDatos();	
			}						
			else 
			{
				JOptionPane.showMessageDialog(null, "Aviso, ya está en el último coche");
			}		
		}			
	}
 		
	//CONSULTAR COCHE EXISTENTE
	public void comprobarMatriculaExistente(String buscarMatricula)
	{	
		if(objConsultasCoches.buscarMatricula(buscarMatricula))
		{
			visualizarDatos();
			posicionarConsulta(buscarMatricula);
			cargarImagen.setEnabled(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Esa matricula no existe","Error",JOptionPane.ERROR_MESSAGE);
			bloquearTextField();
			modificarDatos.setEnabled(false);
			cargarImagen.setEnabled(false);
			posicionarConsulta("2424TTY");
			visualizarDatos();
		}		
	}
	
	//POSICIONAR CONSULTA
	public void posicionarConsulta(String buscarMatricula)
	{
		objConsultasCoches.consultarTodo();
		try {
			while(!objetoBBDD.resultado.getString("matricula").equals(buscarMatricula) )
			{
				objetoBBDD.resultado.next();
			}
		} catch (SQLException e){e.printStackTrace();}
	}
	
	//AÑADIR COCHES
	public void anadirCoches()
	{
		if(!comprobarTextFildVacios())
		{
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}
		else
		{
			asignarDatos();
			objConsultasCoches.anadirCoches(objCoches);
			limpiarTextField();
			posicionarConsulta("2424TTY");
			visualizarDatos();
			bloquearTextField();
			guardarDatos.setEnabled(false);
			cargarImagen.setEnabled(false);
		}
	}
	
	//CONSULTAR COCHE EXISTENTE
	public void comprobarCocheExistente(String buscarMatricula)
	{	
		if(objConsultasCoches.buscarMatricula(buscarMatricula))
		{
			visualizarDatos();
			posicionarConsulta(buscarMatricula);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Esa matrícula no existe","Error",JOptionPane.ERROR_MESSAGE);
			posicionarConsulta("2424TTY");
			visualizarDatos();
		}		
	}
	
	//MODIFICAR DATOS DE COCHES
	public void modificarCoches()
	{
		if(!comprobarTextFildVacios())
		{
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}
		else
		{
			asignarDatos();
			objConsultasCoches.modificarCoches(objCoches);
			limpiarTextField();
			posicionarConsulta("2424TTY");
			visualizarDatos();
			JOptionPane.showMessageDialog(null, "Datos del coche modificados correctamente");
			modificarDatos.setEnabled(false);
		}
	}
	//CONFIRMAR PARA EDITAR DATOS DE COCHES
	public void confirmarEditarCoche()
	{
		if(jtfmatricula.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Elija primero un coche para modificar sus datos");
		}
		else
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "¿Seguro que quiere modificar este coche?",
					"Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			if(respuesta==JOptionPane.YES_OPTION)
			{
				desbloquearTextField();
				cargarComboBox();
				guardarDatos.setEnabled(false);
				modificarDatos.setEnabled(true);
				cargarImagen.setEnabled(true);
				jtfmatricula.setEnabled(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Operación cancelada");
				cargarImagen.setEnabled(false);
				modificarDatos.setEnabled(false);
				posicionarConsulta("2424TTY");
			}
		}		
	}
	
	//CONFIRMAR PARA BORRAR DATOS DE COCHES
	public void confirmarBorrarCoche()
	{
		if(jtfmatricula.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Elija primero un coche para modificar sus datos");
		}
		else
		{
			int respuesta=JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar este coche?",
					"Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			try
			{
				if(respuesta==JOptionPane.YES_OPTION)
				{
					objConsultasCoches.borrarCoches(objetoBBDD.resultado.getString("matricula"));
					limpiarTextField();
					JOptionPane.showMessageDialog(null, "Coche borrado correctamente");
					posicionarConsulta("2424TTY");
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