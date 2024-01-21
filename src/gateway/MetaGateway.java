package gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class MetaGateway implements IGatewayFactory{
	
	private String MetaServerIP;
	private int serverPort;
	private static String DELIMITIER = "#";

	public MetaGateway(String servIP, int servPort){
		MetaServerIP = servIP;
		serverPort = servPort;
	}
	
	public String login(String email, String contrasena) {
		String message = "login" + DELIMITIER + email + DELIMITIER + contrasena;
		String usuario = null;
		
		try (Socket socket = new Socket(MetaServerIP, serverPort)){
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			//Enviar mensaje al servidor de meta
			out.writeUTF(message);
			System.out.println(" -  Enviando datos a '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + message + "'");
			usuario = in.readUTF();
			System.out.println(" - Recibiendo usuario desde "  + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + usuario + "'");
			return usuario;
		} catch (Exception e) {
			System.out.println("# Trans. SocketClient error: " + e);
		}
		return usuario;
	}
	
	public String comprobarUsuario(String email) {
		String message = "comprobarEmail" + DELIMITIER + email;
		String resultado = null;
		
		try (Socket socket = new Socket(MetaServerIP, serverPort)){
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			//Enviar mensaje al servidor de facebook
			out.writeUTF(message);
			System.out.println(" - Enviando datos a '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + message + "'");
			resultado = in.readUTF();
			System.out.println(" - Recibiendo usuario desde "  + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + resultado + "'");
			return resultado;
		} catch (Exception e) {
			System.out.println("# Trans. SocketClient error: " + e);
		}
		return resultado;
	}
}
