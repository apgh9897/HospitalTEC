package modelo;

import java.util.Date;

/**
*
* @author PriscillaGonzalez
*/
public class Bitacora {

  private int idEstado;
  private Date fecha;
  private Date hora;
  private String funcionario;
  private int codigoCita;

  public Bitacora(Date fecha, Date hora, String funcionario, int codigoCita, int idEstado) {
	this.fecha = fecha;
	this.hora= hora;
	this.funcionario = funcionario;
	this.codigoCita = codigoCita;
  }
  
  public int getIdEstado() {
    return idEstado;
  }
  
  public Date getFecha() {
    return fecha;
  }
  
  public Date getHora() {
    return hora;
  }
  
  public String getFuncionario() {
    return funcionario;
  }
  
  public int getCodigoCita() {
    return codigoCita;
  }
  
  public void setidEstado(int pIdEstado) {
	  this.idEstado=pIdEstado;
  }
  
  public void setFecha(Date pFecha) {
    this.fecha = pFecha;
  }
  
  public void setHora(Date pHora) {
    this.hora = pHora;
  }
  
  public void setFuncionario(String pNombreFuncionario) {
    this.funcionario = pNombreFuncionario;
  }
  
  public void setCodigoCita(int pCodigoCita) {
	    this.codigoCita=pCodigoCita;
	  }
  
  public void setFecha2(Date pFecha) {
	    this.fecha=pFecha;
	  }
  
  public void setHora2(Date pHora) {
	    this.hora=pHora;
	  }
  

}
