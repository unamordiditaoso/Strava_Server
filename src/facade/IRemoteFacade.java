package facade;

import java.rmi.Remote;
import java.util.Date;
import java.util.List;

import data.domain.Deporte;
import data.domain.Reto;
import data.domain.TipoReto;

public interface IRemoteFacade extends Remote{
	
	public boolean registro(String correo, String nombre, Date fecha_nacimiento);
	
	public boolean regsitroOpcional(String correo, String nombre, Date fecha_ncto, int peso, int altura, int frec_card_max, int frec_card_reposo);
		
	public String logIn(String correo, String contraseña);
	
	public void logOut();
	
	public List<Reto> getRetosActivos(Date Fecha);
	
	public boolean ApuntarseReto(Reto reto);
	
	public float ComprobarReto();
	
	public List<String> ConsultarEstadoRetos();
	
	public void crearReto(String nombre, Date fecha_ini, Date fecha_fin, TipoReto TipoReto, float distancia, Integer tiempo, Deporte deporte);
	
	public void crearEntrenamiento(String titulo, Deporte deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion);
	
} 
