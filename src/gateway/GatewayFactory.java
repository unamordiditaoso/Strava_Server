package gateway;

import data.domain.TipoRegistro;

public class GatewayFactory {
	private static GatewayFactory instance;
    private String facebookServerIp;
    private int facebookServerPort;
    private GatewayFactory() {}

    public static synchronized GatewayFactory getInstance() {
        if (instance == null) {
            instance = new GatewayFactory();
        }
        return instance;
    }

    public IGatewayFactory createGateway(TipoRegistro tR) {
        if (tR.equals(TipoRegistro.Meta)) {
            return new MetaGateway(facebookServerIp, facebookServerPort);
        } else {
            return new GoogleGateway();
        }
    }

    public void setFacebookServer(String ip, int port) {
        facebookServerIp = ip;
        facebookServerPort = port;
    }
}
