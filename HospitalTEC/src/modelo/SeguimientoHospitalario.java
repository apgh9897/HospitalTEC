package modelo;

/**
*
* @author PriscillaGonzalez
*/
public class SeguimientoHospitalario {
  
  private int numeroSeguimiento;
  private String fechaSeguimiento;
  private String nombreFuncionarioSeguimiento;
  private String observacion;
  private String tratamiento;
  private int idHospitalizacion;

  public SeguimientoHospitalario(String pFechaSeguimiento, String pNombreFuncionarioSeguimiento,
		  String pObservacion, String pTratamiento, int idHospitalizacion) {
	  
	this.setFechaSeguimiento(pFechaSeguimiento);
	this.setNombreFuncionarioSeguimiento(pNombreFuncionarioSeguimiento);
	this.setObservacion(pObservacion);
	this.setTratamiento(pTratamiento);
	this.setIdHospitalizacion(idHospitalizacion);
	
  }

  public int getNumeroSeguimiento() {
	return numeroSeguimiento;
  }

  public String getFechaSeguimiento() {
	return fechaSeguimiento;
  }

  public String getNombreFuncionarioSeguimiento() {
	return nombreFuncionarioSeguimiento;
  }

  public String getObservacion() {
	return observacion;
  }

  public String getTratamiento() {
	return tratamiento;
  }
  
  public int getIdHospitalizacion() {
		return idHospitalizacion;
	}

  public void setNumeroSeguimiento(int pNumeroSeguimiento) {
	this.numeroSeguimiento = pNumeroSeguimiento;
  }

  public void setFechaSeguimiento(String pFechaSeguimiento) {
	this.fechaSeguimiento = pFechaSeguimiento;
  }

  public void setNombreFuncionarioSeguimiento(String pNombreFuncionarioSeguimiento) {
	this.nombreFuncionarioSeguimiento = pNombreFuncionarioSeguimiento;
  }

  public void setObservacion(String pObservacion) {
	this.observacion = pObservacion;
  }

  public void setTratamiento(String pTratamiento) {
	this.tratamiento = pTratamiento;
  }


public void setIdHospitalizacion(int idHospitalizacion) {
	this.idHospitalizacion = idHospitalizacion;
}
  
 
}
