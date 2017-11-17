package clientes;

public class Clientes 
{
	private int codigoCliente;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String poblacion;
	private String codigoPostal;
	private String provincia;
	private String telefono;
	private String fechaNacimiento;
	
	public Clientes()
	{
		super();
		codigoCliente = 0;
		nombre = new String();
		apellidos = new String();
		direccion = new String();
		poblacion = new String();
		codigoPostal = new String();
		provincia = new String();
		telefono = new String();
		fechaNacimiento = new String();
	}
	
	public Clientes(int codigoCliente,String nombre,String apellidos,String direccion,String poblacion,String codigoPostal,String provincia,
			String telefono,String fechaNacimiento)
	{
		super();
		this.codigoCliente = codigoCliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
