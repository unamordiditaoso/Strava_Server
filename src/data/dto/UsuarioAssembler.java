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
