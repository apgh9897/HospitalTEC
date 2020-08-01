package modelo;

import java.util.ArrayList;

/**
*
* @author PriscillaGonzalez
*/
public class Hospitalizacion {
	
  private int numeroHospitalizacion;
  private String nombreCentroDeAtencion;
  private int cedulaPaciente;
  private String nombrePaciente;
  private String diagnostico;
  private String fechaInicio;
  private String fechaFinal;
  private String nombreFuncionario;
  private String especialidad;
  
  private ArrayList<SeguimientoHospitalario> seguimientos;

  public Hospitalizacion() {}
  
  public Hospitalizacion(String pNombreCentroDeAtencion, int pCedulaPaciente,String pNombrePaciente,
		  String pDiagnostico, String pFechaInicio, String pFechaFinal, String pNombreFuncionario, 
		  String pEspecialidad) {
	  
	this.setNombreCentroDeAtencion(pNombreCentroDeAtencion);
	this.setCedulaPaciente(pCedulaPaciente);
	this.setNombrePaciente(pNombrePaciente);
	this.setDiagnostico(pDiagnostico);
	this.setFechaInicio(pFechaInicio);
	this.setFechaFinal(pFechaFinal);
	this.setNombreFuncionario(pNombreFuncionario);
	this.setEspecialidad(pEspecialidad);
	this.setSeguimientos();
	
  }

  public int getNumeroHospitalizacion() {
	return numeroHospitalizacion;
  }

  public String getNombreCentroDeAtencion() {
	return nombreCentroDeAtencion;
  }

  public int getCedulaPaciente() {
	return cedulaPaciente;
  }

  public String getNombrePaciente() {
	return nombrePaciente;
  }

  public String getDiagnostico() {
	return diagnostico;
  }

  public String getFechaInicio() {
	return fechaInicio;
  }

  public String getFechaFinal() {
	return fechaFinal;
  }

  public String getNombreFuncionario() {
	return nombreFuncionario;
  }

  public String getEspecialidad() {
	return especialidad;
  }

  public ArrayList<SeguimientoHospitalario> getSeguimientos() {
	return seguimientos;
  }

  public void setNumeroHospitalizacion(int numeroHospitalizacion) {
	this.numeroHospitalizacion = numeroHospitalizacion;
  }

  public void setNombreCentroDeAtencion(String nombreCentroDeAtencion) {
	this.nombreCentroDeAtencion = nombreCentroDeAtencion;
  }

  public void setCedulaPaciente(int pCedulaPaciente) {
	this.cedulaPaciente = pCedulaPaciente;
  }

  public void setNombrePaciente(String pNombrePaciente) {
	this.nombrePaciente = pNombrePaciente;
  }

  public void setDiagnostico(String pDiagnostico) {
	this.diagnostico = pDiagnostico;
  }

  public void setFechaInicio(String pFechaInicio) {
	this.fechaInicio = pFechaInicio;
  }

  public void setFechaFinal(String pFechaFinal) {
	this.fechaFinal = pFechaFinal;
  }

  public void setNombreFuncionario(String pNombreFuncionario) {
	this.nombreFuncionario = pNombreFuncionario;
  }

  public void setEspecialidad(String pEspecialidad) {
	this.especialidad = pEspecialidad;
  }

  public void setSeguimientos() {
	this.seguimientos = new ArrayList<>();
  }
  

}
