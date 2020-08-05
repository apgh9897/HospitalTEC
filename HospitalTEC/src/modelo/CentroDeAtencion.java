package modelo;

import java.util.ArrayList;

/**
 *
 * @author PriscillaGonzalez
 */
public class CentroDeAtencion {
    
  private int codigoCentro;
  private String nombre;
  private String ubicacion;
  private int capacidadMaxima;
  private String tipoDeCentro;
  private ArrayList<Funcionario> funcionarios;
  private ArrayList<Cita> citas;
  private ArrayList<Paciente> pacientes;

  public CentroDeAtencion() {}

  public CentroDeAtencion(String pNombre, String pUbicacion, 
        int pCapacidadMaxima,String pTipoDeCentro) {
      
    this.setNombre(pNombre); 
    this.setUbicacion(pUbicacion);
    this.setCapacidadMaxima(pCapacidadMaxima);
    this.setTipoDeCentro(pTipoDeCentro);
    this.setFuncionarios();
    this.setCitas();
    this.setPacientes();
    
  }

  public int getCodigoCentro() {
    return codigoCentro;
  }

  public String getNombre() {
    return nombre;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public int getCapacidadMaxima() {
    return capacidadMaxima;
  }

  public String getTipoDeCentro() {
    return tipoDeCentro;
  }
  
  public ArrayList<Funcionario> getFuncionarios(){
	  return funcionarios;
  }
  
  public ArrayList<Cita> getCitas(){
	  return citas;
  }
  
  public ArrayList<Paciente> getPacientes(){
	  return pacientes;
  }

  public void setCodigoCentro(int pCodigoCentro) {
    this.codigoCentro = pCodigoCentro;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public void setUbicacion(String pUbicacion) {
    this.ubicacion = pUbicacion;
  }

  public void setCapacidadMaxima(int pCapacidadMaxima) {
    this.capacidadMaxima = pCapacidadMaxima;
  }

  public void setTipoDeCentro(String pTipoDeCentro) {
    this.tipoDeCentro = pTipoDeCentro;
  }
  
  public void setFuncionarios() {
    this.funcionarios = new ArrayList<>();
  }
  
  public void setCitas() {
    this.citas = new ArrayList<>();
  }
  
  public void setPacientes() {
    this.pacientes = new ArrayList<>();
  }
    
}