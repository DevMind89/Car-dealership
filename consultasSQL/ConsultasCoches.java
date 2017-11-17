package consultasSQL;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import bbdd.ConexionBD;
import coches.Coches;

public class ConsultasCoches 
{
	ConexionBD objetoBBDD = null;

	public ConsultasCoches(ConexionBD objetoBBDD) 
	{
		this.objetoBBDD = objetoBBDD;
	}
	
	//AÑADIR DATOS DE COCHES	
	public void anadirCoches(Coches objCoches) 
	{
		if(!buscarMatricula(objCoches.getMatricula()))
		{
			String anadir="INSERT INTO coches VALUES ('"
					 +objCoches.getMatricula()+"','"
					 +objCoches.getMarca()+"','"
					 +objCoches.getModelo()+"','"
					 +objCoches.getColor()+"',"
					 +objCoches.getPrecio()+",'"
					 +objCoches.getCodigoCliente()+"','"
					 +objCoches.getFotografia()+"')";
			try 
			{
				objetoBBDD.sentencia.executeUpdate(anadir);
				JOptionPane.showMessageDialog(null, "Coche añadido correctamente");
			} catch (SQLException e) {e.printStackTrace();}		
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Error, ya existe un coche con esa matrícula ");
		}		
	}
	
	//BUSCAR COCHES
	public boolean buscarMatricula(String buscarMatricula)
	{
		try
		{
			objetoBBDD.resultado=objetoBBDD.sentencia.executeQuery("SELECT * FROM coches WHERE matricula = '"+buscarMatricula+"'");
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
	
	//MODIFICAR COCHES
	public void modificarCoches(Coches objCoches) 
	{
		String modificar="UPDATE coches SET "
				+"marca='"+objCoches.getMarca()+"',"
				+"modelo='"+objCoches.getModelo()+"',"
				+"color='"+objCoches.getColor()+"',"
				+"precio='"+objCoches.getPrecio()+"',"
				+"codCliente='"+objCoches.getCodigoCliente()+"',"
				+"fotografia='"+objCoches.getFotografia()+"'"
				+" WHERE matricula='"+objCoches.getMatricula()+"'";
		try 
		{
			objetoBBDD.sentencia.executeUpdate(modificar);
		} catch (SQLException e) {e.printStackTrace();}
	}
		
	//BORRAR COCHES
	public void borrarCoches(String matricula)
	{
		String borrar = "DELETE FROM coches WHERE matricula = '"+matricula+"'";
		try
		{
			objetoBBDD.sentencia.executeUpdate(borrar);
		}catch(SQLException e){e.printStackTrace();}
	}
	
	//MOSTRAR TODOS LOS DATOS DE LA BASE DE DATOS
	public boolean consultarTodo()
	{
		try {
			objetoBBDD.resultado=objetoBBDD.sentencia.executeQuery("SELECT * FROM coches");
			objetoBBDD.resultado.first();
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
	}
	
		
	//IR AL PRIMER COCHE
	public boolean consultarPrimerCoche()
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
	
	//IR AL COCHE ANTERIOR
	public boolean consultarCocheAnterior()
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

	//IR AL COCHE SIGUIENTE
	public boolean consultarCocheSiguiente()
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
	
	//IR AL ULTIMO COCHE 	
	public boolean consultarUltimoCoche()
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