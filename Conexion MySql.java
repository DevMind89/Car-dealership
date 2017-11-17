package paquete;
public class Conexion
 {
	 	public Connection conexion=null;
	    public Statement sentencia=null;   		//Se usa para enviar sentencias SQL a la BD
	    public ResultSet resultado=null;   		//Contendrá los datos devueltos de una sentencia SQL
	    
	    public Conexion()
		{
	        conectarBD();
	    }
	    public void conectarBD()
		{
			// Conectar con MySql
			String url = "jdbc:mysql://localhost/Coffeebreak";

			// Cargar el driver y se genera una nueva instancia y crear conexión
			try {
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conexion = DriverManager.getConnection(url, "root", "");
				// Crear sentencia
				sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (Exception e) {e.printStackTrace();}
		}

	    public void cerrarConexionBD()
		{
	        try {
	            resultado.close();
	            sentencia.close();
	            conexion.close();
	        } catch (SQLException ex) {ex.printStackTrace();}	               
	    }
}
