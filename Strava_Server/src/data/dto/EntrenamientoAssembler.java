package data.dto;

import java.util.ArrayList;
import java.util.List;

import data.domain.Entrenamiento;

public class EntrenamientoAssembler {
	private static EntrenamientoAssembler instance;

	private EntrenamientoAssembler() { }
	
	public static EntrenamientoAssembler getInstance() {
		if (instance == null) {
			instance = new EntrenamientoAssembler();
		}

		return instance;
	}

	public EntrenamientoDTO entrenamientoToDTO(Entrenamiento entrenamiento) {
		EntrenamientoDTO dto = new EntrenamientoDTO();
		
		dto.setTitulo(entrenamiento.getTitulo());
		dto.setDistancia(entrenamiento.getDistancia());
		dto.setDuracion(entrenamiento.getDuracion());
		dto.setFechaInicio(entrenamiento.getFecha_ini());
		dto.setFechaFin(entrenamiento.getFecha_fin());
				
		return dto;
	}
	
	public List<EntrenamientoDTO> entrenamientoToDTO(List<Entrenamiento> entrenamientos) {
		List<EntrenamientoDTO> dtos = new ArrayList<>();
		
		for (Entrenamiento entrenamiento : entrenamientos) {
			dtos.add(this.entrenamientoToDTO(entrenamiento));
		}
		
		return dtos;		
	}
}
