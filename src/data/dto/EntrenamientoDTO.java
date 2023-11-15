package data.dto;

import java.io.Serializable;
import java.util.Date;

import data.domain.Deporte;

public class EntrenamientoDTO implements Serializable{
	//This attribute is needed to implement the "Serializable" interface.
		private static final long serialVersionUID = 1L;
		private String titulo;
		private int distancia;
		private Date fechaInicio;
		private Date fechaFin;
		private int duracion;
		private Deporte deporte;
		
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public int getDistancia() {
			return distancia;
		}
		public void setDistancia(int distancia) {
			this.distancia = distancia;
		}
		public Date getFechaInicio() {
			return fechaInicio;
		}
		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public Date getFechaFin() {
			return fechaFin;
		}
		public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
		}
		public int getDuracion() {
			return duracion;
		}
		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}
		public Deporte getDeporte() {
			return deporte;
		}
		public void setDeporte(Deporte deporte) {
			this.deporte = deporte;
		}	
}
