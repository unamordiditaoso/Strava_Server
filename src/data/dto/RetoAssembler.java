package data.dto;

import java.util.ArrayList;
import java.util.List;

import data.domain.Deporte;
import data.domain.Reto;
import data.domain.TipoReto;

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
		List<String> deportes = new ArrayList<>();
		
		dto.setNombre(reto.getNombre());
		dto.setObjetivo(reto.getObjetivo());
		dto.setFechaInicio(reto.getFecha_ini());
		dto.setFechaFin(reto.getFecha_fin());
		dto.setTipoDeReto(reto.getTipo().toString());
		for (Deporte deporte : reto.getDeportes()) {
			deportes.add(deporte.toString());
		}
		dto.setDeportes(deportes);
		return dto;
	}
	
	public List<RetoDTO> retosToDTO(List<Reto> retos) {
		List<RetoDTO> dtos = new ArrayList<>();
		
		for (Reto reto : retos) {
			dtos.add(this.retoToDTO(reto));
		}
		
		return dtos;		
	}
	
	public Reto DTOtoReto(RetoDTO dto) {
		Reto reto = new Reto();
		List<Deporte> deportes = new ArrayList<>();
		reto.setNombre(dto.getNombre());
		reto.setFecha_ini(dto.getFechaInicio());
		reto.setFecha_fin(dto.getFechaFin());
		reto.setObjetivo(dto.getObjetivo());
		reto.setTipo(TipoReto.valueOf(dto.getTipoDeReto()));
		
		for (String deporte : dto.getDeportes()) {
			deportes.add(Deporte.valueOf(deporte));
		}
		reto.setDeportes(deportes);
		
		return reto;
		
	}
	
	public List<Reto> DTOstoRetos(List<RetoDTO> dtos) {
		 List<Reto> retos = new ArrayList<>();

	        for (RetoDTO dto : dtos) {
	           retos.add(this.DTOtoReto(dto)); 
	        }
	        return retos;
	    }
	
}
