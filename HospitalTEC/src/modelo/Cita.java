package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
  private String horaDeCita;
  private String observaciones;
  private Paciente paciente;
  private ArrayList<Bitacora> bitacora;

  public Cita() {}

  public Cita(String pEstadoDeCita, String pAreaDeAplicacion,
        String pHoraDeCita, String pObservaciones, Paciente pPaciente) {
      
    this.setEstadoDeCita(pEstadoDeCita);
    this.setAreaDeAplicacion(pAreaDeAplicacion);
    this.setFechaDeCita();
    setHoraDeCita(pHoraDeCita);
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

  public String getFechaDeCita() {
	  SimpleDateFormat mascara = new SimpleDateFormat("yyyy-MM-dd");
      return mascara.format(fechaDeCita);
  }

  public String getHoraDeCita() {
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

  public void setFechaDeCita() {
      Calendar calendario;
      calendario = Calendar.getInstance();
      this.fechaDeCita = calendario.getTime();
  }

  public void setHoraDeCita(String phorarioCita) {
      this.horaDeCita = phorarioCita;
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
