package data.dto;

import java.util.ArrayList;
import java.util.List;

import data.domain.Usuario;

public class UsuarioAssembler {
	private static UsuarioAssembler instance;

	private UsuarioAssembler() { }
	
	public static UsuarioAssembler getInstance() {
		if (instance == null) {
			instance = new UsuarioAssembler();
		}
		return instance;
	}

	public UsuarioDTO userToDTO(Usuario usuario) {
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setCorreo(usuario.getCorreo());
		dto.setNombre(usuario.getNombre());
		dto.setFecha_ncto(usuario.getFecha_ncto());
		dto.setAltura(usuario.getAltura());
		dto.setPeso(usuario.getPeso());
		dto.setFrec_card_max(usuario.getFrec_card_max());
		dto.setFrec_card_reposo(usuario.getFrec_card_reposo());
		dto.settReg(usuario.gettReg());
		
		return dto;
	}
	
	public List<UsuarioDTO> usuariosToDTO(List<Usuario> usuarios) {
		List <UsuarioDTO> userdtos = new ArrayList<>();
		
		for (Usuario usuario : usuarios) {
			userdtos.add(this.userToDTO(usuario));
		}
		
		return userdtos;
	}

}
