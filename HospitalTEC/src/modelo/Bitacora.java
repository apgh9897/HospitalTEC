package modelo;

import java.util.Date;

/**
*
* @author PriscillaGonzalez
*/
public class Bitacora {

  private int numeroBitacora;
  private Date fechaDeCita;
  private Date horaDeCita;
  private String funcionario;
  private int codigoCita;

  public Bitacora(Date fechaDeCita, Date horaDeCita, String funcionario, int codigoCita) {
	this.fechaDeCita = fechaDeCita;
	this.horaDeCita = horaDeCita;
	this.funcionario = funcionario;
	this.codigoCita = codigoCita;
  }
  
  public int getNumeroBitacora() {
    return numeroBitacora;
  }
  
  public Date getFechaDeCita() {
    return fechaDeCita;
  }
  
  public Date getHoraDeCita() {
    return horaDeCita;
  }
  
  public String getFuncionario() {
    return funcionario;
  }
  
  public int getCodigoCita() {
    return codigoCita;
  }
  
  public void setNumeroBitacora() {}
  
  public void setFechaDeCita(Date pFecha) {
    this.fechaDeCita = pFecha;
  }
  
  public void setHoraDeCita(Date pHora) {
    this.horaDeCita = pHora;
  }
  
  public void setFuncionario(String pNombreFuncionario) {
    this.funcionario = pNombreFuncionario;
  }
  

}
