package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PriscillaGonzalez
 */
public class Paciente {
    
  private int cedula;
  private String nombre;
  private String fechaDeNacimiento;
  private String tipoDeSangre;
  private String nacionalidad;
  private String provincia;
  private String canton;
  private String distrito;
  private ArrayList<Integer> numerosDeTelefono;
  private ArrayList<Cita> citas;
  private ArrayList<Diagnostico> diagnosticos;
  private ArrayList<Tratamiento> tratamientos;

  public Paciente() {}

  public Paciente(int pCedula, String pNombre, String pFechaNacimiento, String pTipoDeSangre,
		  String pNacionalidad, String pProvincia, String pCanton, String pDistrito){
    this.setCedula(pCedula);
    this.setNombre(pNombre);
    this.setFechaDeNacimiento(pFechaNacimiento);
    this.setTipoDeSangre(pTipoDeSangre);
    this.setNacionalidad(pNacionalidad);
    this.setProvincia(pProvincia);
    this.setCanton(pCanton);
    this.setDistrito(pDistrito);
    this.setNumerosDeTelefono();
    this.setCitas();
    this.setDiagnosticos();
    this.setTratamientos();
  }

  public int getCedula() {
    return cedula;
  }

  public String getNombre() {
    return nombre;
  }

  public String getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public String getTipoDeSangre() {
    return tipoDeSangre;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public String getProvincia() {
    return provincia;
  }

  public String getCanton() {
    return canton;
  }

  public String getDistrito() {
    return distrito;
  }

  public ArrayList<Integer> getNumerosDeTelefono() {
    return numerosDeTelefono;
  }
  
  public ArrayList<Cita> getCitas(){
    return citas;
  }
  
  public ArrayList<Diagnostico> getDiagnosticos(){
    return diagnosticos;
  }
  
  public ArrayList<Tratamiento> getTratamiento(){
    return tratamientos;
  }

  public void setCedula(int pCedula) {
    this.cedula = pCedula;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public void setFechaDeNacimiento(String pFechaDeNacimiento) {
    this.fechaDeNacimiento = pFechaDeNacimiento;
  }

  public void setTipoDeSangre(String pTipoDeSangre) {
    this.tipoDeSangre = pTipoDeSangre;
  }

  public void setNacionalidad(String pNacionalidad) {
    this.nacionalidad = pNacionalidad;
  }

  public void setProvincia(String pProvincia) {
    this.provincia = pProvincia;
  }

  public void setCanton(String pCanton) {
    this.canton = pCanton;
  }

  public void setDistrito(String pDistrito) {
    this.distrito = pDistrito;
  }

  public void setNumerosDeTelefono() {
    this.numerosDeTelefono = new ArrayList<>();
  }
  
  public void setCitas() {
    this.citas = new ArrayList<>();
  }
  
  public void setDiagnosticos() {
    this.diagnosticos = new ArrayList<>();
  }
    
  public void setTratamientos() {
    this.tratamientos = new ArrayList<>();
  }
  
  
}
