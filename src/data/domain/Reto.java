package data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reto {
	protected String nombre;
	protected Date fecha_ini;
	protected Date fecha_fin;
	protected TipoReto tipo;
	protected int objetivo;
	protected List<Deporte> deportes;

	public Reto() {
		super();
		this.nombre = "";
		this.fecha_ini = new Date();
		this.fecha_fin = new Date();
		this.tipo = TipoReto.Distancia;
		this.objetivo = 0;
		this.deportes = new ArrayList<>();
	}
	
	public Reto(String nombre, Date fecha_ini, Date fecha_fin, TipoReto tipo, int objetivo, List<Deporte> deportes) {
		super();
		this.nombre = nombre;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.tipo = tipo;
		this.objetivo = objetivo;
		this.deportes = deportes;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public TipoReto getTipo() {
		return tipo;
	}

	public void setTipo(TipoReto tipo) {
		this.tipo = tipo;
	}

	public int getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(int objetivo) {
		this.objetivo = objetivo;
	}

	public List<Deporte> getDeportes() {
		return deportes;
	}

	public void setDeportes(List<Deporte> deportes) {
		this.deportes = deportes;
	}

	@Override
	public String toString() {
		return nombre + " (" + fecha_ini + " - " + fecha_fin + ") ";
	}
}
