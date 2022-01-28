package Entities;

public class DireccionEntrega {

	private String calleSobre;
	private int altura;
	private String piso;
	private int idDireccion;
	private String calleEntre1;
	private String calleEntre2;
	
	public DireccionEntrega() {
		
	}
	public DireccionEntrega(String calleSobre, int altura, String piso, String calleEntre1, String calleEntre2) {
		super();
		this.calleSobre = calleSobre;
		this.altura = altura;
		this.piso = piso;
		this.calleEntre1 = calleEntre1;
		this.calleEntre2 = calleEntre2;
	}
	public String getCalleSobre() {
		return calleSobre;
	}
	public void setCalleSobre(String calleSobre) {
		this.calleSobre = calleSobre;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public int getIdDireccion() {
		return idDireccion;
	}
	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}
	public String getCalleEntre1() {
		return calleEntre1;
	}
	public void setCalleEntre1(String calleEntre1) {
		this.calleEntre1 = calleEntre1;
	}
	public String getCalleEntre2() {
		return calleEntre2;
	}
	public void setCalleEntre2(String calleEntre2) {
		this.calleEntre2 = calleEntre2;
	}
	
	
}
