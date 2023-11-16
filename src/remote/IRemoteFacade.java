package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import data.domain.Deporte;
import data.domain.Reto;
import data.domain.TipoReto;
import data.domain.Usuario;
import data.dto.RetoDTO;

public interface IRemoteFacade extends Remote{
	
//  No hay que hacerlo para esta semana	
//	public void registro(String correo, String nombre, Date fecha_nacimiento) throws RemoteException;
//	
//	public void regsitroOpcional(String correo, String nombre, Date fecha_ncto, int peso, int altura, int frec_card_max, int frec_card_reposo) throws RemoteException;
		
	public long logIn(String correo, String password) throws RemoteException;
	
	public void logOut(long token) throws RemoteException;
	
	public List<RetoDTO> getRetos(long token) throws RemoteException;
	
	public List<RetoDTO> getRetosActivos(long token) throws RemoteException;
	
	public boolean ApuntarseReto(long token, RetoDTO retoDTO) throws RemoteException;
	
	public float ComprobarReto(long token, RetoDTO retoDTO) throws RemoteException;
	
	public List<String> ConsultarEstadoRetos(long token) throws RemoteException;
	
	public void crearReto( String nombre, Integer objetivo, TipoReto tipo, Date fecha_ini, Date fecha_fin, List<Deporte> deportes) throws RemoteException;
	
	public void crearEntrenamiento(String titulo, Deporte deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion) throws RemoteException;
	
} 
