package facade;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.domain.Deporte;
import data.domain.Reto;
import data.domain.TipoReto;
import data.domain.Usuario;
import data.dto.UsuarioAssembler;
import services.EntrenamientoAppService;
import services.RetoAppService;
import services.UserAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{
	private static final long serialVersionUID = 1L;
	private EntrenamientoAppService entrenamientoService = new EntrenamientoAppService();
	private RetoAppService retoService = new RetoAppService();
	private UserAppService userService = new UserAppService();
	protected Map<Long, Usuario> stateServer = new HashMap<>();
	protected Map<String, String> usuariosRegistrados = new HashMap<>();
	
	protected RemoteFacade() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

//      No hay que hacerlo para esta semana
		public void registro(String correo, String nombre, Date fecha_nacimiento) throws RemoteException{
//				System.out.println("* RemoteFacade registro(). Nombre usuario:" + nombre);
//				Usuario usuario = userService.registro(nombre, correo, fecha_nacimiento);
//				
//				if(usuario != null) {
//					usuariosRegistrados.put(correo, "1111");
//					
//				} else {
//					throw new RemoteException("El registro no se ha completado correctamente");
//				}
		}
//		
		public void regsitroOpcional(String correo, String nombre, Date fecha_ncto, int peso, int altura, int frec_card_max, int frec_card_reposo) throws RemoteException {
//			System.out.println("*RemoteFacade registro(). Nombre de usuario:" + nombre);
//			Usuario usuario = userService.registroOpcional(nombre, correo, fecha_ncto, peso, altura, frec_card_max, frec_card_reposo);
//			
//			if (usuario != null) {
//				usuariosRegistrados.put(correo, "1111");
//			} else {
//				throw new RemoteException("El registro no se ha completado correctamente");
//			}
		}
		
		
	public synchronized long logIn(String correo, String password) throws RemoteException {
			System.out.println("\" * RemoteFacade login(): \" + email + \" / \" + password");
			Usuario usuario = userService.login(correo, password);
			
			if(usuario != null) {
				if(this.stateServer.values().contains(usuario)) {
					Long token = Calendar.getInstance().getTimeInMillis();
					this.stateServer.put(token, usuario);
					return token;
				} else {
					throw new RemoteException("El usuario ya ha iniciado sesion");
				}
			} else {
				throw new RemoteException("No se ha podido iniciar sesion correctamente");
			}
		}

	public void logOut(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout(): " + token);
		if (this.stateServer.containsKey(token)) {
			this.stateServer.remove(token);
		} else {
			throw new RemoteException("El usuario aun no ha iniciado sesion");
		}
	}

	public List<Reto> getRetosActivos(Date Fecha) throws RemoteException {
		System.out.println(" * RemoteFacade getRetosActivos()");
		return null;
	}
	@Override
	public boolean ApuntarseReto(Reto reto) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public float ComprobarReto() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<String> ConsultarEstadoRetos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void crearReto(String nombre, Date fecha_ini, Date fecha_fin, TipoReto TipoReto, float distancia,
			Integer tiempo, Deporte deporte) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void crearEntrenamiento(String titulo, Deporte deporte, Integer distancia, Date fecha_ini, Date fecha_fin,
			Integer duracion) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
