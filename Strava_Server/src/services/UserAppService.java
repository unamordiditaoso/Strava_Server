package services;

import data.domain.Usuario;

public class UserAppService {

	public Usuario login(String correo, String password) {
		//TODO: Get User using DAO and check 		
		Usuario usuario = new Usuario();		
		usuario.setCorreo("thomas.e2001@gmail.com");
		usuario.setNombre("Thomas");
		
		if (usuario.getCorreo().equals(correo) && usuario.comprobarCorreo(correo)) {		
			return usuario;
		} else {
			return null;
		}
	}	
	
}
