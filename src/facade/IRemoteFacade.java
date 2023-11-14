package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import data.domain.Deporte;
import data.domain.Reto;
import data.domain.TipoReto;

public interface IRemoteFacade extends Remote{
	
	public void registro(String correo, String nombre, Date fecha_nacimiento) throws RemoteException;
	
	public boolean regsitroOpcional(String correo, String nombre, Date fecha_ncto, int peso, int altura, int frec_card_max, int frec_card_reposo) throws RemoteException;
		
	public String logIn(String correo, String contraseña) throws RemoteException;
	
	public void logOut() throws RemoteException;
	
	public List<Reto> getRetosActivos(Date Fecha) throws RemoteException;
	
	public boolean ApuntarseReto(Reto reto) throws RemoteException;
	
	public float ComprobarReto() throws RemoteException;
	
	public List<String> ConsultarEstadoRetos() throws RemoteException;
	
	public void crearReto(String nombre, Date fecha_ini, Date fecha_fin, TipoReto TipoReto, float distancia, Integer tiempo, Deporte deporte) throws RemoteException;
	
	public void crearEntrenamiento(String titulo, Deporte deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion) throws RemoteException;
	
} 
