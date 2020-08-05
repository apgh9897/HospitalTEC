package modelo;

/**
*
* @author PriscillaGonzalez
*/
public class Tratamiento {
	
  private int codigo;
  private String nombre;
  private int dosis;
  private String tipoTratamiento;
  
  
  public Tratamiento(String pNombre, int pDosis, String pTipoTratamiento) {
	this.setNombre(pNombre);
	this.setDosis(pDosis);
	this.setTipoTratamiento(pTipoTratamiento);
  }

  public int getCodigo() {
	return codigo;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public int getDosis() {
    return dosis;
  }
  
  public String getTipoTratamiento() {
    return tipoTratamiento;
  }
  
  public void setCodigo(int pCodigo) {
    this.codigo = pCodigo;
  }
  
  public void setNombre(String pNombre) {
	this.nombre = pNombre;
  }
  
  public void setDosis(int pDosis) {
	this.dosis = pDosis;
  }
  
  public void setTipoTratamiento(String pTipoTratamiento) {
	this.tipoTratamiento = pTipoTratamiento;
  }
  
  

}
