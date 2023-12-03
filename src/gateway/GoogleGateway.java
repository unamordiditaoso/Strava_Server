package gateway;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class GoogleGateway {
	private static final String BASE_URL = "http://127.0.0.1:10000/";
	
	private static GoogleGateway instance = new GoogleGateway();
	
	private GoogleGateway() {
	}
	
	public static GoogleGateway getInstance() {
		return instance;
	}
	
	public boolean login(String email, String contrasena) {
		if (comprobarUsuario(email)) {
			try {
		    	HttpClient client = HttpClient.newHttpClient();
		    	
		    	HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create(BASE_URL + "contr?email=" + email))
		                .build();
		    	
		    	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		    	
		    	if (response.body().equals(contrasena)) {
					return true;
				}
		    	
		    } catch (Exception ex) {
		    	System.out.println("   # Error en logIn(): " + ex.getMessage());
		    }
		} 
		return false;
    }

	public boolean comprobarUsuario(String email) {
		    try {
		    	HttpClient client = HttpClient.newHttpClient();
		    	
		    	HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create(BASE_URL + "validar?email=" + email))
		                .build();
		    	
		    	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		    	
		    	if (response.body().equals("true")) {
					return true;
				}
		    	
		    } catch (Exception ex) {
		    	System.out.println("   # Error en comprobarUsuario(): " + ex.getMessage());
		    }
		    
	    return false;
	}
}
