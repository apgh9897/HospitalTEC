package modelo;

/**
*
* @author PriscillaGonzalez
*/
public class Diagnostico {
  
  private int codigo;
  private String nombre;
  private String nivel;
  private String observaciones;
  private Tratamiento tratamiento;
  
  public Diagnostico() {}
  
  public Diagnostico(int pCodigo, String pNombre, String pNivel, String pObservaciones, Tratamiento pTratamiento) {
	this.setCodigo(pCodigo);
    this.setNivel(pNivel);
    this.setNombre(pNombre);
    this.setObservaciones(pObservaciones);
    this.setTratamiento(pTratamiento);
  }
  
  public int getCodigo() {
    return codigo;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public String getNivel() {
    return nivel;
  }
  
  public String getObservaciones() {
   return observaciones;
  }
  
  public Tratamiento getTratamiento() {
    return tratamiento;
  }
  
  public void setCodigo(int pCodigo) {
	  this.codigo = pCodigo;
  }
  
  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }
  
  public void setNivel(String pNivel) {
    this.nivel = pNivel;
  }
  
  public void setObservaciones(String pObservaciones) {
    this.observaciones = pObservaciones;	  
  }
  
  public void setTratamiento(Tratamiento pTratamiento) {
    this.tratamiento = pTratamiento;
  }
  

}
