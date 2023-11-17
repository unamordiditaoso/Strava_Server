package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import data.domain.Deporte;
import data.domain.Entrenamiento;
import data.domain.Reto;
import data.domain.TipoReto;
import data.domain.Usuario;

public class RetoAppService {
	public Reto crearReto(String nombre, Integer objetivo, String tipo, Date fecha_ini, Date fecha_fin, List<String> deportes, Usuario usuario) {
		Reto reto = new Reto();
		List<Deporte> deportess = new ArrayList<>();
		deportes.forEach(d->{
			deportess.add(Deporte.valueOf(d));
		});
		if (TipoReto.valueOf(tipo) == TipoReto.Distancia) {
			reto.setNombre(nombre);
			reto.setObjetivo(objetivo);
			reto.setTipo(TipoReto.valueOf(tipo));
			reto.setFecha_ini(fecha_ini);
			reto.setFecha_fin(fecha_fin);
			reto.setDeportes(deportess);
		} else {
			reto.setNombre(nombre);
			reto.setObjetivo(objetivo);
			reto.setTipo(TipoReto.valueOf(tipo));
			reto.setFecha_ini(fecha_ini);
			reto.setFecha_fin(fecha_fin);
			reto.setDeportes(deportess);
		}
		usuario.add(reto);
	
		return reto;
	}
	
	public List<Reto> getRetos(Usuario usuario) {
		List<Reto> retos = new ArrayList<>();
		
		usuario.getRetos().forEach(r ->{
				if (!retos.contains(r)) {
					retos.add(r);
				}
			
		});
		
		return retos;
	}
	
	public List<Reto> getRetosActivos(Usuario usuario){
		List<Reto> retosAct = new ArrayList<>();
		
		Date hoy = new Date(Calendar.getInstance().getTimeInMillis());
		usuario.getRetos().forEach(r ->{
			if (r.getFecha_ini().before(hoy) && r.getFecha_fin().after(hoy)) {
				if (!retosAct.contains(r)) {
					retosAct.add(r);
				}
			}
		});
		
		return retosAct;
	}
	
	public float comprobarReto(Usuario usuario, Reto reto) {
		int obj = 0;
		float porcentaje = 0;
		
		for (Entrenamiento e : usuario.getEntrenamientos()) {
			if (reto.getDeportes().contains(e.getDeporte())) {
				if (reto.getFecha_ini().before(e.getFecha_ini()) && reto.getFecha_fin().after(e.getFecha_fin())) {	
					if (reto.getTipo().equals(TipoReto.Distancia)) {
						obj = obj + e.getDistancia();						
					} else if (reto.getTipo().equals(TipoReto.Tiempo)) {
						obj = obj + e.getDuracion();
					}
				}
			}
		}
		
		if (obj < reto.getObjetivo()) {
			
			porcentaje = ((float) obj  * 100 / (float) reto.getObjetivo());
		} else if (obj >= reto.getObjetivo()) {
			porcentaje = 100;
		}
		
		return porcentaje;		
	}
	
	public boolean apuntarseReto(Usuario usuario, Reto reto) {
		if (reto != null && !usuario.getRetos().contains(reto)) {
			usuario.add(reto);
			System.out.println(String.format("Te has apuntado al reto: %s", reto.toString()));
			return true;
		} else {
			return false;
		}	
	}
	
	
	public List<String> consultarEstadoRetos(Usuario usuario){
		List<String> estadoRetos = new ArrayList<String>();
		
		if (usuario.getRetos().size() != 0) {
			usuario.getRetos().forEach(r -> {
				
				float por = comprobarReto(usuario, r);
				
				String est = por + "% - Reto: " + r.toString();
				estadoRetos.add(est);
			});	
		}
		
		return estadoRetos;
	}
}
