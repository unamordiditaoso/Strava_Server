package services;

import java.util.ArrayList;
import java.util.Date;

import data.domain.Deporte;
import data.domain.Entrenamiento;

public class EntrenamientoAppService {
	public Entrenamiento crearEntrenamiento(String titulo, String deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion) {
		Entrenamiento entrenamiento = new Entrenamiento();
		
		
		entrenamiento.setTitulo(titulo);
		entrenamiento.setDeporte(Deporte.valueOf(deporte));
		entrenamiento.setDistancia(distancia);
		entrenamiento.setFecha_ini(fecha_ini);
		entrenamiento.setFecha_fin(fecha_fin);
		entrenamiento.setDistancia(distancia);
		
		return entrenamiento;
	}
}
