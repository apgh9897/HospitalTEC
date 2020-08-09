package modelo;

import java.util.ArrayList;

/**
*
* @author PriscillaGonzalez
*/
public class Administrador extends Usuario{


  private ArrayList<String[][]> catalogoDiagnostico; 
  private ArrayList<String> tipoDeCentro;
  private ArrayList<String> areaEspecialidad;
  private ArrayList<CentroDeAtencion> centrosDeAtencion;
  

  

  public Administrador() {
	super();
}

public Administrador(String pUsuario, String pContrasenna) {
	super(pUsuario, pContrasenna);

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
