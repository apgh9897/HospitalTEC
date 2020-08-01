package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PriscillaGonzalez
 */
public class Funcionario {

  private int cedula;
  private String nombre;
  private String tipoDeFuncionario;
  private Date fechaDeIngreso;
  private ArrayList<String> areaDeTrabajo;
  private CentroDeAtencion centroDeAtencion;
  
  public Funcionario() {}

  public Funcionario(int pCedula, String pNombre, String pTipoDeFuncionario, CentroDeAtencion pCentroDeAtencion) {
    this.setCedula(pCedula);
    this.setNombre(pNombre);
    this.setTipoDeFuncionario(pTipoDeFuncionario);
    this.setFechaDeIngreso();
    this.setAreaDeTrabajo();
    this.setCentroDeAtencion(pCentroDeAtencion);
  }

  public int getCedula() {
    return cedula;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTipoDeFuncionario() {
    return tipoDeFuncionario;
  }

  public Date getFechaDeIngreso() {
    return fechaDeIngreso;
  }

  public ArrayList<String> getAreaDeTrabajo() {
    return areaDeTrabajo;
  }
  
  public CentroDeAtencion getCentroDeAtencion() {
    return centroDeAtencion;
  }

  public void setCedula(int pCedula) {
    this.cedula = pCedula;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public void setTipoDeFuncionario(String pTipoDeFuncionario) {
    this.tipoDeFuncionario = pTipoDeFuncionario;
  }

  public void setFechaDeIngreso() {
    //this.fechaDeIngreso = pFechaDeIngreso;
  }

  public void setAreaDeTrabajo() {
    this.areaDeTrabajo = new ArrayList<>();
  }
  
  public void setCentroDeAtencion(CentroDeAtencion pCentroDeAtencion) {
	  this.centroDeAtencion = pCentroDeAtencion;
  }
    
}