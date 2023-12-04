package services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.domain.TipoRegistro;
import data.domain.Usuario;
import data.dto.RetoDTO;

public class UserAppService {
	static List<String> correos = new ArrayList<String>();
	Map<String, String> mInicioSesion = new HashMap<String, String>();
	Map<String, Usuario> usuariosRegistrados = new HashMap<>();

	public Usuario login(String correo, String password) {
		//TODO: Get User using DAO and check 		
		Usuario usuario = usuariosRegistrados.get(correo);
		
		System.out.println(usuario.getNombre());
		//Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);	
		mInicioSesion.put(correo, sha1);
		return usuario;
//		if (mInicioSesion.containsKey(correo) && mInicioSesion.get(correo).equals(sha1)) {		
//			
//		} else {
//			return null;
//		}
	}
	
	public Usuario registro(String nombre, String correo, Date fecha_ncto, String tipReg, Integer peso, Integer altura, Integer frecuenciaCardMax, Integer frecuenciaCardRep) {
		Usuario usuario = new Usuario();
		
		if (!correos.contains(usuario.getCorreo())) {
			correos.add(correo);
			usuario.setNombre(nombre);
			usuario.setCorreo(correo);
			usuario.setFecha_ncto(fecha_ncto);
		usuario.settReg(TipoRegistro.valueOf(tipReg));
			if (peso != null && altura != null && frecuenciaCardMax != null && frecuenciaCardRep != null) {
				usuario.setPeso(peso);
				usuario.setAltura(altura);
				usuario.setFrec_card_max(frecuenciaCardMax);
				usuario.setFrec_card_reposo(frecuenciaCardRep);
			}
			usuariosRegistrados.put(correo, usuario);
		} else {
			System.err.println("El correo ya esta registrado");
			return null;
		}
		
		
		return usuario;
	} 
	
}
