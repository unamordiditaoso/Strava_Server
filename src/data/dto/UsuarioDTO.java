package data.dto;

import java.io.Serializable;
import java.util.Date;

import data.domain.TipoRegistro;

public class UsuarioDTO implements Serializable{
	//This attribute is needed to implement the "Serializable" interface.
		private static final long serialVersionUID = 1L;	
		private String nombre;
		private String correo;
		private Date fecha_ncto;
		private int altura;
		private int peso;
		private int frec_card_max;
		private int frec_card_reposo;
		private TipoRegistro tReg;
		
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
		public Date getFecha_ncto() {
			return fecha_ncto;
		}
		public void setFecha_ncto(Date fecha_ncto) {
			this.fecha_ncto = fecha_ncto;
		}
		public int getAltura() {
			return altura;
		}
		public void setAltura(int altura) {
			this.altura = altura;
		}
		public int getPeso() {
			return peso;
		}
		public void setPeso(int peso) {
			this.peso = peso;
		}
		public int getFrec_card_max() {
			return frec_card_max;
		}
		public void setFrec_card_max(int frec_card_max) {
			this.frec_card_max = frec_card_max;
		}
		public int getFrec_card_reposo() {
			return frec_card_reposo;
		}
		public void setFrec_card_reposo(int frec_card_reposo) {
			this.frec_card_reposo = frec_card_reposo;
		}
		public TipoRegistro gettReg() {
			return tReg;
		}
		public void settReg(TipoRegistro tReg) {
			this.tReg = tReg;
		}
		
		
}
