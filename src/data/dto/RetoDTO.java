package data.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import data.domain.Deporte;
import data.domain.TipoReto;

public class RetoDTO implements Serializable{
	//This attribute is needed to implement the "Serializable" interface.
			private static final long serialVersionUID = 1L;
			private String nombre;
			private int objetivo;
			private Date fechaInicio;
			private Date fechaFin;
			private TipoReto tipoDeReto;
			private List<Deporte> deportes;
			
			public String getNombre() {
				return nombre;
			}
			public void setNombre(String nombre) {
				this.nombre = nombre;
			}
			public int getObjetivo() {
				return objetivo;
			}
			public void setObjetivo(int objetivo) {
				this.objetivo = objetivo;
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
			public TipoReto getTipoDeReto() {
				return tipoDeReto;
			}
			public void setTipoDeReto(TipoReto tipoDeReto) {
				this.tipoDeReto = tipoDeReto;
			}
			public List<Deporte> getDeportes() {
				return deportes;
			}
			public void setDeportes(List<Deporte> deportes) {
				this.deportes = deportes;
			}		
}
