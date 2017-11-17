package consultasSQL;

import java.sql.SQLException;

import bbdd.ConexionBD;

public class ConsultasInformes
{
	ConexionBD objetoBBDD = null;
	ConsultasCoches objConsultasCoches = null;
	
	public ConsultasInformes(ConexionBD objetoBBDD)
	{
		this.objetoBBDD = objetoBBDD;
		objConsultasCoches = new ConsultasCoches(objetoBBDD);
	}
	
	//AÑADIR DATOS BUSCANDO POR CODIGO DE CLIENTES
	public boolean consultarCliente(int codCliente)
	{
		String consulta="SELECT clientes.nombre,clientes.apellidos,coches.matricula,numRevision,cambAceite,cambFiltro,revFrenos"
				+ " FROM clientes INNER JOIN coches ON clientes.codCliente=coches.codCliente"
				+ " INNER JOIN revisiones ON coches.matricula=revisiones.matricula WHERE clientes.codCliente="+codCliente;
		
		try {
			objetoBBDD.resultado=objetoBBDD.sentencia.executeQuery(consulta);
			if(objetoBBDD.resultado.first()) 
			{
				return true;
			}
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	
	//AÑADIR DATOS BUSCANDO POR MATRICULA
	public boolean consultarCoche(String matricula)
	{
		String consulta="SELECT clientes.nombre,clientes.apellidos,coches.matricula,numRevision,cambAceite,cambFiltro,revFrenos"
				+ " FROM clientes INNER JOIN coches ON clientes.codCliente=coches.codCliente"
				+ " INNER JOIN revisiones ON coches.matricula=revisiones.matricula WHERE coches.matricula='"+matricula+"';";
		try {
			objetoBBDD.resultado=objetoBBDD.sentencia.executeQuery(consulta);
			if(objetoBBDD.resultado.first()) 
			{
				return true;
			}
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}
}
