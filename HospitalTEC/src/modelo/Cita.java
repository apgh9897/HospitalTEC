package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PriscillaGonzalez
 */
public class Cita {
    
  private int identificador;
  private String estadoDeCita;
  private String areaDeAplicacion;
  private Date fechaDeCita;
  private Date horaDeCita;
  private String observaciones;
  private Paciente paciente;
  private ArrayList<Bitacora> bitacora;

  public Cita() {}

  public Cita(int pIdentificador, String pEstadoDeCita, String pAreaDeAplicacion,
        Date pFechaDeCita, Date pHoraDeCita, String pObservaciones, Paciente pPaciente) {
      
    this.setIdentificador(pIdentificador);
    this.setEstadoDeCita(pEstadoDeCita);
    this.setAreaDeAplicacion(pAreaDeAplicacion);
    this.setFechaDeCita(pFechaDeCita);
    this.setHoraDeCita(pHoraDeCita);
    this.setObservaciones(pObservaciones);
    this.setPaciente(pPaciente);
    
  }

  public int getIdentificador() { 
    return identificador;
  }

  public String getEstadoDeCita() {
    return estadoDeCita;
  }

  public String getAreaDeAplicacion() {
    return areaDeAplicacion;
  }

  public Date getFechaDeCita() {
    return fechaDeCita;
  }

  public Date getHoraDeCita() {
    return horaDeCita;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public Paciente getPaciente() {
    return paciente;
  }
  
  public ArrayList<Bitacora> getBitacora(){
    return bitacora;
  }

  public void setIdentificador(int pIdentificador) {
    this.identificador = pIdentificador;
  }

  public void setEstadoDeCita(String pEstadoDeCita) {
    this.estadoDeCita = pEstadoDeCita;
  }

  public void setAreaDeAplicacion(String pAreaDeAplicacion) {
    this.areaDeAplicacion = pAreaDeAplicacion;
  }

  public void setFechaDeCita(Date pFechaDeCita) {
    this.fechaDeCita = pFechaDeCita;
  }

  public void setHoraDeCita(Date pHoraDeCita) {
    this.horaDeCita = pHoraDeCita;
  }

  public void setObservaciones(String pObservaciones) {
    this.observaciones = pObservaciones;
  }

  public void setPaciente(Paciente pPaciente) {
    this.paciente = pPaciente;
  }
  
  public void setBitacora() {
    this.bitacora = new ArrayList<>();
  }
 

}
