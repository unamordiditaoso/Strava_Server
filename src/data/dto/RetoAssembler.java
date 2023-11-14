package data.dto;

import java.util.ArrayList;
import java.util.List;

import data.domain.Reto;

public class RetoAssembler {
	private static RetoAssembler instance;

	private RetoAssembler() { }
	
	public static RetoAssembler getInstance() {
		if (instance == null) {
			instance = new RetoAssembler();
		}

		return instance;
	}

	public RetoDTO retoToDTO(Reto reto) {
		RetoDTO dto = new RetoDTO();
		
		dto.setNombre(reto.getNombre());
		dto.setObjetivo(reto.getObjetivo());
		dto.setFechaInicio(reto.getFecha_ini());
		dto.setFechaFin(reto.getFecha_fin());
		dto.setTipoDeReto(reto.getTipo().ordinal());
				
		return dto;
	}
	
	public List<RetoDTO> retoToDTO(List<Reto> retos) {
		List<RetoDTO> dtos = new ArrayList<>();
		
		for (Reto reto : retos) {
			dtos.add(this.retoToDTO(reto));
		}
		
		return dtos;		
	}
}
