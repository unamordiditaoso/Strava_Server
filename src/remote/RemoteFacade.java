package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.domain.Deporte;
import data.domain.Entrenamiento;
import data.domain.Reto;
import data.domain.TipoRegistro;
import data.domain.TipoReto;
import data.domain.Usuario;
import data.dto.EntrenamientoAssembler;
import data.dto.EntrenamientoDTO;
import data.dto.RetoAssembler;
import data.dto.RetoDTO;
import data.dto.UsuarioAssembler;
import data.dto.UsuarioDTO;
import gateway.GoogleGateway;
import gateway.MetaGateway;
import services.EntrenamientoAppService;
import services.RetoAppService;
import services.UserAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{
	private static final long serialVersionUID = 1L;
	private EntrenamientoAppService entrenamientoService = new EntrenamientoAppService();
	private RetoAppService retoService = new RetoAppService();
	private UserAppService userService = new UserAppService();
	private MetaGateway metaGateway = new MetaGateway("0.0.0.0", 8001);
	private GoogleGateway googleGateway = new GoogleGateway();
	
	
	protected Map<Long, Usuario> stateServer = new HashMap<>();
	protected Map<String, String> usuariosRegistrados = new HashMap<>();
	protected List<RetoDTO> retos = new ArrayList<>();
	protected List<Usuario> usuarios = new ArrayList<>();
	
	public RemoteFacade() throws RemoteException {
		super();
	}


	public void registro(String nombre, String correo, Date fecha_ncto, String tipoReg, Integer peso, Integer altura, Integer frecuenciaCardMax, Integer frecuenciaCardRep, String contrasena) throws RemoteException{
			System.out.println("* RemoteFacade registro(). Nombre usuario:" + nombre);
			Usuario usuario = userService.registro(nombre, correo, fecha_ncto, tipoReg, peso, altura, frecuenciaCardMax, frecuenciaCardRep);
			
			if (tipoReg.equals("Meta")) {
				String hash = metaGateway.login(correo, contrasena);
				if(usuario != null && hash.equals("true")) {
					usuariosRegistrados.put(correo, contrasena);
					usuarios.add(usuario);
				} else {
					System.out.println("El registro no se ha completado correctamente");
				}
			} else if (tipoReg.equals("Google")) {
				if (googleGateway.login(correo, contrasena).equals("true")) {
					usuariosRegistrados.put(correo, contrasena);
					usuarios.add(usuario);
				} else {
					System.out.println("El registro no se ha completado correctamente");
				}
			}
	}
		
	public synchronized long logIn(String correo, String password) throws RemoteException {
			System.out.println(" * RemoteFacade login(): " + correo + " " + password);
			Usuario usuario = userService.login(correo, password);
			Long token;
			if(usuario != null) {
				if(usuario.gettReg().equals(TipoRegistro.Meta)) {
					System.out.println("\n* RemoteFacade loginMeta(). Email: " + correo + ", contraseña: " + password);
					String hash = metaGateway.login(correo, password);
					if(hash.equals("true")) {
						token = Calendar.getInstance().getTimeInMillis();
			        	this.stateServer.put(token, usuario);
			        	return token;
					} else {
						throw new RemoteException("Login() meta failed \n");
					}
				} else if(usuario.gettReg().equals(TipoRegistro.Google)) {
					System.out.println("\n* RemoteFacade loginGoogle(). Email: " + correo + ", contraseña: " + password);
					if(googleGateway.login(correo, password).equals("true")) {
						token = Calendar.getInstance().getTimeInMillis();
						this.stateServer.put(token, usuario);
						return token;
					} else {
						throw new RemoteException("Login() google failed \n");
					}
				}
			}
			return 01;
	}
		
	 public synchronized boolean comprobarEmailMeta(String email) throws RemoteException {
	    	System.out.println("\n* RemoteFacade comprobarEmail(). Email: " + email);
	    	
	    	String devuelve = metaGateway.comprobarUsuario(email);
	    	
	    	if(!devuelve.equals("")) {
	    		if(devuelve.equals("true")) {
	    			return true;
	    		} else {
	    			throw new RemoteException("Email is not loged in Facebook");
	    		}
	    	} else {
	    		throw new RemoteException("comprobarMail() failed");
	    	}
	    }
	    
	    public synchronized boolean comprobarEmailGoogle(String email) throws RemoteException {
	    	System.out.println("\n* RemoteFacade comprobarEmail(). Email: " + email);
	    	
	    	String result = googleGateway.comprobarUsuario(email);
	    	
	    	if(result.equals("true")) {
	    		return true;
	    	} else {
	    		throw new RemoteException("comprobarEmail() failed");
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
	
	public Map<String, String> getUsuarios() throws RemoteException {
		System.out.println(" * RemoteFacade getUsuarios()");
		return usuariosRegistrados;
	}
	
	public List<RetoDTO> getRetos() throws RemoteException {
		System.out.println(" * RemoteFacade getRetos()");
		List<RetoDTO> retosAct = new ArrayList<>();
		if(retos.size() > 0) {
			Date hoy = new Date(Calendar.getInstance().getTimeInMillis());
			retos.forEach(r ->{
				if (r.getFechaInicio().before(hoy) && r.getFechaFin().after(hoy)) {
					if (!retosAct.contains(r)) {
						retosAct.add(r);
					}
				}
			});
			return retosAct;
		} else {
			throw new RemoteException("No se han podido recuperar todos retos");
		}

	}
	
	public List<RetoDTO> getRetos(long token) throws RemoteException {
		System.out.println(" * RemoteFacade getRetos()");
		List<Reto> retos = retoService.getRetos(this.stateServer.get(token));
		if(retos.size() > 0) {
			return RetoAssembler.getInstance().retosToDTO(retos);
		} else {
			throw new RemoteException("No se han podido recuperar tus retos");
		}

	}

	public List<RetoDTO> getRetosActivos(long token) throws RemoteException {
		System.out.println(" * RemoteFacade getRetosActivos()");
		List<Reto> retosActivos = retoService.getRetosActivos(this.stateServer.get(token));
		if(retosActivos.size() > 0) {
			return RetoAssembler.getInstance().retosToDTO(retosActivos);
		} else {
			throw new RemoteException("No se han podido recuperar tus retos activos");
		}
	}
	
	public boolean ApuntarseReto(long token, RetoDTO retoDTO) throws RemoteException {
		Reto reto = RetoAssembler.getInstance().DTOtoReto(retoDTO);
		System.out.println("\n* RemoteFacade ApuntarseReto(). Usuario: " + this.stateServer.get(token).getNombre() + ", reto: " + reto.getNombre());
		if(retoService.apuntarseReto(this.stateServer.get(token), reto)) {
			return true;
		} else {
			return false;
		}
	}

	public float ComprobarReto(long token, RetoDTO retoDTO) throws RemoteException {
		Reto reto = RetoAssembler.getInstance().DTOtoReto(retoDTO);
		
		System.out.println("\n* RemoteFacade ComprobarReto(). Usuario: " + this.stateServer.get(token).getNombre() + ", reto: " + reto.getNombre());
		float porcentaje = retoService.comprobarReto(this.stateServer.get(token), reto);
		return porcentaje;
	
	}

	public List<String> ConsultarEstadoRetos(long token) throws RemoteException {
		List<String> estadoRetos = retoService.consultarEstadoRetos(this.stateServer.get(token));
		System.out.println("\n* RemoteFacade ConsultarEstadoRetos()");
		if (estadoRetos != null) {
			return estadoRetos;
		} else {
			throw new RemoteException("ConsultarEstadoRetos() ha fallado");
		}
	}
	
	@Override
	public void crearReto(String nombre, Integer objetivo, String tipo, Date fecha_ini, Date fecha_fin, List<String> deportes, long token) throws RemoteException {
		System.out.println(" * RemoteFacade crearReto nombre : " + nombre + " | fecha_inicio " + fecha_ini + " | fecha_fin " + fecha_fin + " | TipoReto " + tipo);
						
		Reto reto = retoService.crearReto(nombre, objetivo, tipo, fecha_ini, fecha_fin, deportes, this.stateServer.get(token));
		
		List<String> nomR = new ArrayList<String>();
		
		retos.forEach(r -> nomR.add(r.getNombre()));
		
		if (reto != null) {
			if (!nomR.contains(reto.getNombre())) {
				retos.add(RetoAssembler.getInstance().retoToDTO(reto));
			}
		} else {
			throw new RemoteException("crearReto() ha fallado");
		}

	}
	@Override
	public void crearEntrenamiento(String titulo, String deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion, long token) throws RemoteException {
		System.out.println(" * RemoteFacade crearEntrenamiento titulo : " + titulo + " | distancia " + distancia + " | fecha_fin " + fecha_fin + " | fecha_fin " + fecha_fin + " | duracion: " + duracion);
		
		Entrenamiento entrenamiento = entrenamientoService.crearEntrenamiento(titulo, deporte, distancia, fecha_ini, fecha_fin, duracion, this.stateServer.get(token));
		
		if (entrenamiento != null) {
			System.out.println("Se ha creado el entrenamiento correctamente");
		} else {
			throw new RemoteException("crearEntrenamiento() ha fallado");
		}
		
	}
	
	public List<EntrenamientoDTO> getEntrenamientos(long token) throws RemoteException {
        System.out.println(" * RemoteFacade getEntrenamientos()");
        
        List<Entrenamiento> entrenamientos = entrenamientoService.getEntrenamientos(this.stateServer.get(token));
        System.out.println(this.stateServer.get(token));
        if(entrenamientos.size() > 0) {
            return EntrenamientoAssembler.getInstance().entrenamientoToDTO(entrenamientos);
        } else {
            throw new RemoteException("No se han podido recuperar tus entrenamientos");
        }

    }


}
