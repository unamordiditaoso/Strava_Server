package data.domain;

import java.util.Date;
import java.util.List;


import javax.persistence.*;


@Entity
@Table(name = "Entrenamiento")
public class Entrenamiento {
	@Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    long id;
	protected String titulo;
	protected Deporte deporte;
	protected int distancia;
	protected Date fecha_ini;
	protected Date fecha_fin;
	protected int duracion;
	@ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "id_U") 
    private Usuario usuario;
	
	public Entrenamiento() {
		super();
		this.titulo = "";
		this.deporte = Deporte.Running;
		this.distancia = 0;
		this.fecha_ini = new Date();
		this.fecha_fin = new Date();
		this.duracion = 0;
		this.usuario = new Usuario();
	}
	
	public Entrenamiento(String titulo, Deporte deporte, int distancia, Date fecha_ini, Date fecha_fin, int duracion, Usuario usuario) {
		super();
		this.titulo = titulo;
		this.deporte = deporte;
		this.distancia = distancia;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.duracion = duracion;
		this.usuario = usuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Deporte getDeporte() {
		return deporte;
	}
	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}
	public int getDistancia() {
		return this.distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
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
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Entrenamiento [titulo=" + titulo + ", deporte=" + deporte + ", distancia=" + distancia + ", fecha_ini="
				+ fecha_ini + ", fecha_fin=" + fecha_fin + ", duracion=" + duracion + "]";
	}
	
}