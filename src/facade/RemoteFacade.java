package facade;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import data.domain.Usuario;
import services.EntrenamientoAppService;
import services.RetoAppService;
import services.UserAppService;

public class RemoteFacade {
	private EntrenamientoAppService entrenamientoService = new EntrenamientoAppService();
	private RetoAppService retoService = new RetoAppService();
	private UserAppService userService = new UserAppService();
	protected Map<Long, String> stateServer = new HashMap<>();
	protected Map<String, String> usuariosRegistrados = new HashMap<>();
	
	public void registro(String correo, String nombre, Date fecha_nacimiento) throws RemoteException{
			System.out.println("* RemoteFacade registro(). Nombre usuario:" + nombre);
			Usuario usuario = userService.registro(nombre, correo, fecha_nacimiento);
			
			if(usuario != null) {
				usuariosRegistrados.put(correo, "IñakiMaricon");
				
			} else {
				throw new RemoteException("El registro no se ha completado correctamente");
			}
	}

}
