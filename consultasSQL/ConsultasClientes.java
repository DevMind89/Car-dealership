package consultasSQL;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import bbdd.ConexionBD;
import clientes.Clientes;

public class ConsultasClientes 
{
	ConexionBD objetoBBDD = null;

	public ConsultasClientes(ConexionBD objetoBBDD) 
	{
		this.objetoBBDD = objetoBBDD;
	}
	
	//AÑADIR DATOS DE CLIENTES	
	public void anadirClientes(Clientes objClientes) 
	{
		if(!buscarClientes(objClientes.getCodigoCliente()))
		{
			String anadir="INSERT INTO clientes VALUES ("
					+objClientes.getCodigoCliente()+",'"													 
					+objClientes.getNombre()+"','"
					 +objClientes.getApellidos()+"','"
					 +objClientes.getDireccion()+"','"
					 +objClientes.getPoblacion()+"','"
					 +objClientes.getCodigoPostal()+"','"
					 +objClientes.getProvincia()+"','"
					 +objClientes.getTelefono()+"','"
					 +objClientes.getFechaNacimiento()+"')";
			try {
				objetoBBDD.sentencia.executeUpdate(anadir);
				JOptionPane.showMessageDialog(null, "Cliente añadido correctamente");
			} catch (SQLException e) {e.printStackTrace();}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Error, ya existe un cliente con ese código");
		}		
	}

	//BUSCAR CLIENTES
	public boolean buscarClientes(int buscarClientes)
	{
		try
		{
			objetoBBDD.resultado = objetoBBDD.sentencia.executeQuery("SELECT * FROM clientes WHERE codCliente = "+buscarClientes);
			if(objetoBBDD.resultado.first())
			{
				return true;
			}
			else
			{
				return false;
			}								
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	
	//MODIFICAR CLIENTES
	public void modificarCliente(Clientes objClientes) 
	{
		String modificar="UPDATE clientes SET "
				+ "nombre='"+objClientes.getNombre()+"',"
				+"apellidos='"+objClientes.getApellidos()+"',"
				+"direccion='"+objClientes.getDireccion()+"',"
				+"poblacion='"+objClientes.getPoblacion()+"',"
				+"codPostal='"+objClientes.getCodigoPostal()+"',"
				+"provincia='"+objClientes.getProvincia()+"',"
				+"telefono='"+objClientes.getTelefono()+"',"
				+"fechaNac='"+objClientes.getFechaNacimiento()+"'"
				+" WHERE codCliente="+objClientes.getCodigoCliente();
		try 
		{
			objetoBBDD.sentencia.executeUpdate(modificar);
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	//BORRAR CLIENTES
	public void borrarCliente(int codCliente)
	{
		String borrar = "DELETE FROM clientes WHERE codCliente = "+codCliente;
		try
		{
			objetoBBDD.sentencia.executeUpdate(borrar);
		}catch(SQLException e){e.printStackTrace();}
	}
	
	//MOSTRAR TODOS LOS DATOS DE LA BASE DE DATOS
	public boolean consultarTodo()
	{
		try 
		{
			objetoBBDD.resultado=objetoBBDD.sentencia.executeQuery("SELECT * FROM clientes");
			objetoBBDD.resultado.first();
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
	}
	
	//IR AL PRIMER CLIENTE
	public boolean consultarClientePrimero()
	{
		try
		{
			if(!objetoBBDD.resultado.isFirst())
			{
				objetoBBDD.resultado.first();
				return true;
			}					
		} catch (SQLException e1) {	e1.printStackTrace();}
		return false;		
	}
	
	//IR AL CLIENTE ANTERIOR
	public boolean consultarClienteAnterior()
	{
		try 
		{
			if(!objetoBBDD.resultado.isFirst())
			{
				objetoBBDD.resultado.previous();
				return true;
			}					
		} catch (SQLException e1) {	e1.printStackTrace();}
		return false;
	}

	//IR AL CLIENTE SIGUIENTE
	public boolean consultarClienteSiguiente()
	{
		try
		{
			if(!objetoBBDD.resultado.isLast())
			{
				objetoBBDD.resultado.next();
				return true;
			}					
		} catch (SQLException e1) {	e1.printStackTrace();}
		return false;
	}
	
	//IR AL ULTIMO CLIENTE 	
	public boolean consultarClienteUltimo()
	{
		try 
		{
			if(!objetoBBDD.resultado.isLast())
			{
				objetoBBDD.resultado.last();
				return true;
			}					
		} catch (SQLException e1) {	e1.printStackTrace();}
		return false;			
	}
}
