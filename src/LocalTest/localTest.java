package LocalTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.domain.Deporte;
import data.domain.TipoReto;
import data.dto.RetoDTO;
import data.dto.UsuarioDTO;
import remote.RemoteFacade;



public class localTest {

	public static void main(String[] args) {
		 RemoteFacade facade = null;

	        Map<String, String> contrasenaUsuario = new HashMap<>();

	        Calendar c = Calendar.getInstance();
	        Calendar c2 = Calendar.getInstance();
	        
	        c.set(2023, 12, 9);
	        c2.set(2023, 9, 1);
	        
	        Date date = c.getTime();
	        Date date2 = c2.getTime();

	        long token = 0l;

	        UsuarioDTO usuario  = new UsuarioDTO();
	        usuario.setNombre("igna");
	        usuario.setCorreo("i@gmail.com");
	        
	        List<String> deportes = new ArrayList<>();
	        deportes.add("Ciclismo");
	        deportes.add("Running");
	        
	        RetoDTO reto = new RetoDTO();
	        reto.setNombre("Corre 40 kms");
	        reto.setFechaFin(date);
	        reto.setFechaInicio(date2);
	        reto.setObjetivo(40);
	        reto.setDeportes(deportes);
	        reto.setTipoDeReto("Distancia");
	           
	        List<RetoDTO> retos = new ArrayList<>();
	        List<String> deportess = new ArrayList<>();
	        deportess.add("Ciclismo");
	        deportess.add("Running");

	        try {
	            facade = new RemoteFacade();

	            //facade.registro("i@gmail.com", "igna", date, 80, 190, 210, 60);

	            contrasenaUsuario.put("igna", "1111");
	            
	            token = facade.logIn("i@gmail.com", "1111");

	            facade.crearEntrenamiento("ruta por el monte", "Ciclismo", 100, date, date2, 47, token);

	            facade.crearReto("5 minutos a sprint", 100, "Distancia", date, date2, deportess, token);
	            
	            retos = facade.getRetos(token);
	            
	            for (RetoDTO retoDTO : retos) {
	            	System.out.println("\t-" + retoDTO.getNombre() + " " + retoDTO.getDeportes());		
	            }
	            
	            facade.ApuntarseReto(token, reto);

	            List<RetoDTO> retosActivos = facade.getRetosActivos(token);
	            for (RetoDTO retoDTO : retosActivos) {
	                System.out.println("\t- " + retoDTO.getNombre() + ". Deportes: " + reto.getDeportes() + ". Tipo de reto: " + reto.getTipoDeReto());
	            }

	            List<String> estadoRetos = facade.ConsultarEstadoRetos(token);
	            
	            estadoRetos.forEach(e -> System.out.println("\t - " + e));

	        } catch (Exception e) {
	            System.err.println("\t#Error: " + e + "\n");
	        }

	        try{
	            token = facade.logIn(usuario.getCorreo(), "1111");
	            
	            facade.logOut(token);

	            token = facade.logIn("iñaki", "0000");

	            facade.logOut(token);
	        } catch (Exception e) {
	            System.out.println("\t#Error " + e);
	        }
	    }

	

}
