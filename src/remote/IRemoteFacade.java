package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import data.domain.Deporte;
import data.domain.Reto;
import data.domain.TipoReto;
import data.domain.Usuario;
import data.dto.EntrenamientoDTO;
import data.dto.RetoDTO;
import data.dto.UsuarioDTO;

public interface IRemoteFacade extends Remote{
	
//  No hay que hacerlo para esta semana	
	public void registro(String nombre, String correo, Date fecha_ncto, Integer peso, Integer altura, Integer frecuenciaCardMax, Integer frecuenciaCardRep, String contrasena) throws RemoteException;

//	public void regsitroOpcional(String contr, String correo, String nombre, Date fecha_ncto, int peso, int altura, int frec_card_max, int frec_card_reposo) throws RemoteException;
	
	public Map<String, String> getUsuarios() throws RemoteException;
	
	public long logIn(String correo, String password) throws RemoteException;
	
	public void logOut(long token) throws RemoteException;
	
	public List<RetoDTO> getRetos() throws RemoteException;
	
	public List<RetoDTO> getRetos(long token) throws RemoteException;
		
	public List<RetoDTO> getRetosActivos(long token) throws RemoteException;
	
	public boolean ApuntarseReto(long token, RetoDTO retoDTO) throws RemoteException;
	
	public float ComprobarReto(long token, RetoDTO retoDTO) throws RemoteException;
	
	public List<String> ConsultarEstadoRetos(long token) throws RemoteException;
	
	public void crearReto( String nombre, Integer objetivo, String tipo, Date fecha_ini, Date fecha_fin, List<String> deportes, long token) throws RemoteException;
	
	public void crearEntrenamiento(String titulo, String deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion, long token) throws RemoteException;
	
	public List<EntrenamientoDTO> getEntrenamientos(long token) throws RemoteException;
} 
