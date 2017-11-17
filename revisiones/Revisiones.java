package revisiones;

public class Revisiones 
{
	private int numeroRevision;
	private boolean cambioAceite;
	private boolean cambioFiltro;
	private boolean revisionFrenos;
	private String matricula;
	
	public Revisiones()
	{
		super();
		numeroRevision = 0;
		cambioAceite = true;
		cambioFiltro = true;
		revisionFrenos = true;
		matricula = new String();
	}
	
	public Revisiones(int numeroRevision,boolean cambioAceite,boolean cambioFiltro,boolean revisionFrenos,String matricula)
	{
		super();
		this.numeroRevision = numeroRevision;
		this.cambioAceite = cambioAceite;
		this.cambioFiltro = cambioFiltro;
		this.revisionFrenos = revisionFrenos;
		this.matricula = matricula;
	}

	public int getNumeroRevision() {
		return numeroRevision;
	}

	public void setNumeroRevision(int numeroRevision) {
		this.numeroRevision = numeroRevision;
	}

	public boolean isCambioAceite() {
		return cambioAceite;
	}

	public void setCambioAceite(boolean cambioAceite) {
		this.cambioAceite = cambioAceite;
	}

	public boolean isCambioFiltro() {
		return cambioFiltro;
	}

	public void setCambioFiltro(boolean cambioFiltro) {
		this.cambioFiltro = cambioFiltro;
	}

	public boolean isRevisionFrenos() {
		return revisionFrenos;
	}

	public void setRevisionFrenos(boolean revisionFrenos) {
		this.revisionFrenos = revisionFrenos;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
