package coches;

public class Coches 
{
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private float precio;
	private String codigoCliente;
	private String fotografia;
	
	public Coches()
	{
		super();
		matricula = new String();
		marca = new String();
		modelo = new String();
		color = new String();
		precio = 0;
		codigoCliente = new String();
		fotografia = new String();
	}
	
	public Coches(String matricula,String marca,String modelo,String color,float precio,String codigoCliente,String fotografia)
	{
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
		this.codigoCliente = codigoCliente;
		this.fotografia = fotografia;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getFotografia() {
		return fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}
}
