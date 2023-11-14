package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.domain.Usuario;

public class UserAppService {
	static List<String> correos = new ArrayList<String>();
	Map<String, String> mInicioSesion = new HashMap<String, String>();

	public Usuario login(String correo, String password) {
		//TODO: Get User using DAO and check 		
		Usuario usuario = new Usuario();		
		usuario.setCorreo("thomas.e2001@gmail.com");
		usuario.setNombre("Thomas");
		
		//Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");	
		mInicioSesion.put(correo, sha1);
		
		if (mInicioSesion.containsKey(correo) && mInicioSesion.get(correo).equals(sha1)) {		
			return usuario;
		} else {
			return null;
		}
	}
	
	public Usuario registro(String nombre, String correo, Date fecha_ncto) {
		Usuario usuario = new Usuario();
		
		if (comprobarCorreo(correo)) {
			correos.add(correo);
		
			usuario.setNombre(nombre);
			usuario.setCorreo(correo);
			usuario.setFecha_ncto(fecha_ncto);
		}
		
		
		return usuario;
	} 
	
	public Usuario registroOpcional(String nombre, String correo, Date fecha_ncto, Integer peso, Integer altura, Integer frecuenciaCardMax, Integer frecuenciaCardRep){
		Usuario usuario = registro(nombre, correo, fecha_ncto);
		
		usuario.setPeso(peso);
		usuario.setAltura(altura);
		usuario.setFrec_card_max(frecuenciaCardMax);
		usuario.setFrec_card_reposo(frecuenciaCardRep);
		
		return usuario;
	}
	
	public boolean comprobarCorreo(String correo) {
		if (correos.contains(correo)) {
			return true;
		} else {
			return false;
		}
	}
}
