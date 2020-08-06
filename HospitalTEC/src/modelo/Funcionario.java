package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PriscillaGonzalez
 */
public class Funcionario extends Usuario {

  private String nombre;
  private String tipoDeFuncionario;
  private String fechaDeIngreso;
  private String areaDeTrabajo;
  private CentroDeAtencion centroDeAtencion;

  public Funcionario() {
	super();
  }

  public String getNombre() {
    return nombre;
  }

  public String getTipoDeFuncionario() {
    return tipoDeFuncionario;
  }

  public String getFechaDeIngreso() {
    return fechaDeIngreso;
  }

  public String getAreaDeTrabajo() {
    return areaDeTrabajo;
  }
  
  public CentroDeAtencion getCentroDeAtencion() {
    return centroDeAtencion;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public void setTipoDeFuncionario(String pTipoDeFuncionario) {
    this.tipoDeFuncionario = pTipoDeFuncionario;
  }

  public void setFechaDeIngreso(String pFechaDeIngreso) {
    this.fechaDeIngreso = pFechaDeIngreso;
  }

  public void setAreaDeTrabajo(String pAreaDeTrabajo) {
    this.areaDeTrabajo = pAreaDeTrabajo;
  }
  
  public void setCentroDeAtencion(CentroDeAtencion pCentroDeAtencion) {
	  this.centroDeAtencion = pCentroDeAtencion;
  }
    
}