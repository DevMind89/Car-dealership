package consultasSQL;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import bbdd.ConexionBD;
import revisiones.Revisiones;

public class ConsultasRevisiones
{
	ConexionBD objetoBBDD = null; 
	
	public ConsultasRevisiones(ConexionBD objetoBBDD) 
	{
		this.objetoBBDD = objetoBBDD;
	}
	
	//AÑADIR DATOS DE REVISIONES	
	public boolean anadirRevisiones(Revisiones objRevisiones) 
	{		
		int aceite,filtro,frenos;
		
		if(objRevisiones.isCambioAceite()) {aceite=1;}
		else {aceite=0;}
		
		if(objRevisiones.isCambioFiltro()) {filtro=1;}
		else {filtro=0;}
		
		if(objRevisiones.isRevisionFrenos()) {frenos=1;}
		else {frenos=0;}
		
		String anadir="INSERT INTO revisiones VALUES("+objRevisiones.getNumeroRevision()+","
													+aceite+","+filtro+","+frenos+",'"+objRevisiones.getMatricula()+"')";		
		try 
		{
			objetoBBDD.sentencia.executeUpdate(anadir);
			JOptionPane.showMessageDialog(null, "Revisión añadida correctamente");
			return true;
		} catch (SQLException e) 
		{JOptionPane.showMessageDialog(null, "Ya existe ese número de revisión");return false;}
	}
	
	//BUSCAR REVISIONES
	public boolean buscarNumeroRevisiones(int buscarRevision)
	{
		try
		{
			objetoBBDD.resultado=objetoBBDD.sentencia.executeQuery("SELECT * FROM revisiones WHERE numRevision = "+buscarRevision);
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
	
	//MODIFICAR REVISIONES
	public boolean modificarRevisiones(Revisiones objRevisiones) 
	{
		int aceite,filtro,frenos;
		
		if(objRevisiones.isCambioAceite()) {aceite=1;}
		else {aceite=0;}
		
		if(objRevisiones.isCambioFiltro()) {filtro=1;}
		else {filtro=0;}
		
		if(objRevisiones.isRevisionFrenos()) {frenos=1;}
		else {frenos=0;}
		
		String modificar = "UPDATE revisiones SET cambAceite="+aceite
												+",cambFiltro="+filtro
												+",revFrenos="+frenos
												+",matricula='"+objRevisiones.getMatricula()
												+"' WHERE numRevision="+objRevisiones.getNumeroRevision();
		try {
			objetoBBDD.sentencia.executeUpdate(modificar);
			return true;
		} catch (SQLException e) {return false;}
	}
		
	//BORRAR REVISIONES
	public void borrarRevisiones(int numeroRevision)
	{
		String borrar = "DELETE FROM revisiones WHERE numRevision = "+numeroRevision;
		try
		{
			objetoBBDD.sentencia.executeUpdate(borrar);
		}catch(SQLException e){e.printStackTrace();}
	}
	
	//MOSTRAR TODOS LOS DATOS DE LA BASE DE DATOS
	public boolean consultarTodo()
	{
		try {
			objetoBBDD.resultado=objetoBBDD.sentencia.executeQuery("SELECT * FROM revisiones");
			objetoBBDD.resultado.first();
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
	}
	
		
	//IR A LA PRIMERA REVISION
	public boolean consultarPrimeraRevision()
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
	
	//IR A LA REVISION ANTERIOR
	public boolean consultarRevisionAnterior()
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

	//IR A LA REVISION SIGUIENTE
	public boolean consultarSiguienteRevision()
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
	
	//IR A LA ULTIMA REVISION	
	public boolean consultarUltimaRevision()
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

