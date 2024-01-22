package main;

import java.rmi.Naming;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import data.domain.Deporte;
import data.domain.Entrenamiento;
import data.domain.Reto;
import data.domain.TipoRegistro;
import data.domain.TipoReto;
import data.domain.Usuario;
import remote.IRemoteFacade;
import remote.RemoteFacade;

public class Main {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		//Activate Security Manager. It is needed for RMI.
//				if (System.getSecurityManager() == null) {
//					System.setSecurityManager(new SecurityManager());
//				}

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
			
		//Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade();	
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Strava Server v1 '" + name + "' started!!");
		} catch (Exception ex) {
			System.err.println(" # Strava Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		
		String serverName = System.getProperty("server.name");
		System.out.println("Server Name: " + serverName);
	}
}
