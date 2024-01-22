package data.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	protected String correo;
	protected String nombre;
	protected Date fecha_ncto;
	protected int altura;
	protected int peso;
	protected int frec_card_max;
	protected int frec_card_reposo;
	protected TipoRegistro tReg;
	
	@OneToMany(mappedBy = "aceptado", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reto> retosAceptados = new ArrayList<>();

    @ManyToMany(mappedBy = "usuariosCompletados", fetch = FetchType.EAGER)
    private List<Reto> retosCompletados = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

	
	public Usuario() {
		super();
		this.correo = "";
		this.nombre = "";
		this.fecha_ncto = new Date();
		this.altura = 0;
		this.peso = 0;
		this.frec_card_max = 0;
		this.frec_card_reposo = 0;
		this.tReg = TipoRegistro.Google;
		this.retosAceptados = new ArrayList<Reto>();
        this.retosCompletados = new ArrayList<Reto>();
        this.entrenamientos = new ArrayList<Entrenamiento>();
	}
	
	
	
	public Usuario(String correo, String nombre, Date fecha_ncto, TipoRegistro tReg) {
		super();
		this.correo = correo;
		this.nombre = nombre;
		this.fecha_ncto = fecha_ncto;
		this.tReg = tReg;
		this.altura = 0;
		this.peso = 0;
		this.frec_card_max = 0;
		this.frec_card_reposo = 0;
		this.retosAceptados = new ArrayList<Reto>();
        this.retosCompletados = new ArrayList<Reto>();
        this.entrenamientos = new ArrayList<Entrenamiento>();
	}



	public Usuario(String correo, String nombre, Date fecha_ncto, int altura, int peso, int frec_card_max,
			int frec_card_reposo, TipoRegistro tReg) {
		super();
		this.correo = correo;
		this.nombre = nombre;
		this.fecha_ncto = fecha_ncto;
		this.altura = altura;
		this.peso = peso;
		this.frec_card_max = frec_card_max;
		this.frec_card_reposo = frec_card_reposo;
		this.tReg = tReg;
		this.retosAceptados = new ArrayList<Reto>();
        this.retosCompletados = new ArrayList<Reto>();
        this.entrenamientos = new ArrayList<Entrenamiento>();
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}
	public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}
	public List<Reto> getRetosCompletados() {
        return retosCompletados;
    }

    public void anadirRetoCompletado(Reto reto) {
        this.retosCompletados.add(reto);
    }

    public List<Reto> getRetosAceptados() {
        return retosAceptados;
    }

    public void anadirRetoAceptados(Reto reto) {
        this.retosAceptados.add(reto);
    }
    
    public void agregarSesion(Entrenamiento entrenamiento) {
        entrenamientos.add(entrenamiento);
        entrenamiento.setUsuario(this);
    }
	public void add(Entrenamiento entrenamiento) {
		if (entrenamiento != null && !this.entrenamientos.contains(entrenamiento)) {
			entrenamientos.add(entrenamiento);
		} 	
	}
}

