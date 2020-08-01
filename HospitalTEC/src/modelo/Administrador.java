package modelo;

import java.util.ArrayList;

/**
*
* @author PriscillaGonzalez
*/
public class Administrador {

  private int cedula;
  private String contrasenna;
  private ArrayList<String[][]> catalogoDiagnostico; 
  private ArrayList<String> tipoDeCentro;
  private ArrayList<String> areaEspecialidad;
  private ArrayList<CentroDeAtencion> centrosDeAtencion;
  
  public Administrador(int pCedula, String pContrasenna) {
	this.setCedula(pCedula);
	this.setContrasenna(pContrasenna);
	this.setCatalogoDiagnostico();
	this.setTipoDeCentro();
	this.setAreaEspecialidad();
  }

  public int getCedula() {
	return cedula;
  }

  public String getContrasenna() {
	return contrasenna;
  }

  public ArrayList<String> getAreaEspecialidad() {
	return areaEspecialidad;
  }

  public ArrayList<String[][]> getCatalogoDiagnostico() {
	return catalogoDiagnostico;
  }

  public ArrayList<String> getTipoDeCentro() {
	return tipoDeCentro;
  }
  
  public ArrayList<CentroDeAtencion> getCentrosDeAtencion() {
		return centrosDeAtencion;
  }

  public void setCedula(int pCedula) {
	this.cedula = pCedula;
  }

  public void setContrasenna(String pContrasenna) {
	this.contrasenna = pContrasenna;
  }

  public void setTipoDeCentro() {
	this.tipoDeCentro = new ArrayList<>();
  }

  public void setAreaEspecialidad() {
	this.areaEspecialidad = new ArrayList<>();
  }

  public void setCatalogoDiagnostico() {
	this.catalogoDiagnostico = new ArrayList<>();
  }
  
  public void setCentrosDeAtencion() {
		this.centrosDeAtencion = new ArrayList<>();
  }
  

}
