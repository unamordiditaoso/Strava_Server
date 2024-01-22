package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.EntrenamientoDAO;
import dao.UsuarioDAO;
import data.domain.Deporte;
import data.domain.Entrenamiento;
import data.domain.Reto;
import data.domain.Usuario;
import data.dto.EntrenamientoDTO;

public class EntrenamientoAppService {
	
	public Entrenamiento crearEntrenamiento(String titulo, String deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion, Usuario usuario) {
		Entrenamiento entrenamiento = new Entrenamiento();
		
		entrenamiento.setTitulo(titulo);
		entrenamiento.setDeporte(Deporte.valueOf(deporte));
		entrenamiento.setDistancia(distancia);
		entrenamiento.setFecha_ini(fecha_ini);
		entrenamiento.setFecha_fin(fecha_fin);
		entrenamiento.setDistancia(distancia);
		entrenamiento.setDuracion(duracion);
		
		//Usuario usr = UsuarioDAO.getInstance().find(usuario.getNombre());

         usuario.agregarSesion(entrenamiento);

       // EntrenamientoDAO.getInstance().guardar(entrenamiento);
       // UsuarioDAO.getInstance().guardar(usr);
		
		usuario.add(entrenamiento);
		
		return entrenamiento;
	}
	
	public List<Entrenamiento> getEntrenamientos(Usuario usuario) {
		List<Entrenamiento> entrenamientos = new ArrayList<>();
		
		usuario.getEntrenamientos().forEach(r ->{
				if (!entrenamientos.contains(r)) {
					entrenamientos.add(r);
				}
			
		});
		
		return entrenamientos;
	}
}
