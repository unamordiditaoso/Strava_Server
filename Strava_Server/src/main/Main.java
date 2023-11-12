package main;

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

public class Main {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
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
		
		u.crearEntrenamiento(e1);
		u.crearEntrenamiento(e2);
		u.crearEntrenamiento(e3);
		
		u.crearReto(r1);
		boolean b = u.apuntarseReto(r2);
		
		float f = r1.comprobarReto(u);
		System.out.println(f);
		float f1 = r2.comprobarReto(u);
		System.out.println(f1);
		Date hL = new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute());
		
		List<Reto> rA = u.getRetosActivos(hL);
		
		rA.forEach(r -> System.out.println(r));
	}
}
