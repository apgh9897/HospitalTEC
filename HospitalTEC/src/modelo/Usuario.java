package modelo;

public class Usuario {
	
  protected String usuario;
  protected String contrasenna;
	
  public Usuario() {}
  
  public Usuario(String pUsuario, String pContrasenna) {
    this.setUsuario(pUsuario);
    this.setContrasenna(pContrasenna);
  }
  
  public String getUsuario() {
    return usuario;
  }
  
  public String getContrasenna() {
    return contrasenna;
  }
  
  public void setUsuario(String pUsuario) {
    this.usuario = pUsuario;
  }
  
  public void setContrasenna(String pContrasenna) {
    this.contrasenna = pContrasenna;
  }

}
