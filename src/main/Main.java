package main;

import java.rmi.Naming;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
		Date d = new Date(2003, 04, 27);
		
		Date d1 = new Date(2023, 11, 12, 12, 20);
		Date d2 = new Date(2023, 11, 12, 12, 50);
		Date d3 = new Date(2023, 11, 20, 14, 00);
		Date d4 = new Date(2023, 11, 20, 14, 30);
		Date d5 = new Date(2023, 12, 01, 11, 30);
		Date d6 = new Date(2023, 12, 01, 12, 00);
		
		Date d7 = new Date(2023, 11, 01, 00, 00);
		Date d8 = new Date(2023, 12, 01, 00, 00);
		
		Date d9 = new Date(2023, 11, 13, 00, 00);
		Date d10 = new Date(2023, 12, 01, 00, 00);
		
		Usuario u = new Usuario("i.prieto@opendeusto.es", "unamordiditaoso", d, TipoRegistro.Google);
		
		Entrenamiento e1 = new Entrenamiento("Vuelta al mundo en 30 mins", Deporte.Running, 20, d1, d2, 30);
		Entrenamiento e2 = new Entrenamiento("Vuelta al mundo en 10 mins", Deporte.Ciclismo, 15, d3, d4, 10);
		Entrenamiento e3 = new Entrenamiento("Vuelta al mundo en 20 mins", Deporte.Running, 10, d5, d6, 20);
		
		
		
		Reto r1 = new Reto("Reto de tiempo", d7, d8, TipoReto.Tiempo, 50, Arrays.asList(Deporte.values()));
		Reto r2 = new Reto("Reto de distancia", d9, d10, TipoReto.Distancia, 40, Arrays.asList(Deporte.Ciclismo));
	}
}
