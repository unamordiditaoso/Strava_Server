package data.dto;

import java.io.Serializable;
import java.util.Date;

import data.domain.TipoRegistro;

public class UsuarioDTO implements Serializable{
	//This attribute is needed to implement the "Serializable" interface.
		private static final long serialVersionUID = 1L;	
		private String nombre;
		private String correo;
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getCorreo() {
			return correo;
		}
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		
		
}
