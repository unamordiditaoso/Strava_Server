package services;

import java.util.ArrayList;
import java.util.Date;

import data.domain.Deporte;
import data.domain.Entrenamiento;
import data.domain.Usuario;

public class EntrenamientoAppService {
	public Entrenamiento crearEntrenamiento(String titulo, String deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion, Usuario usuario) {
		Entrenamiento entrenamiento = new Entrenamiento();
		
		entrenamiento.setTitulo(titulo);
		entrenamiento.setDeporte(Deporte.valueOf(deporte));
		entrenamiento.setDistancia(distancia);
		entrenamiento.setFecha_ini(fecha_ini);
		entrenamiento.setFecha_fin(fecha_fin);
		entrenamiento.setDistancia(distancia);
		
		usuario.add(entrenamiento);
		
		return entrenamiento;
	}
}
