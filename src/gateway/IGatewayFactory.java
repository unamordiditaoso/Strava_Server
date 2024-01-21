package gateway;

public interface IGatewayFactory {
	String login(String email, String contrasena);
	String comprobarUsuario(String email);
}
