package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.polydiploma.CeremonyManagerService;
import fr.polydiploma.CeremonyManagerServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class CeremonyManagerApi {
    public CeremonyManagerService ceremony;

    public CeremonyManagerApi(String host, String port) {
        this.initCeremony(host, port);
    }

    private void initCeremony(String host, String port) {
        URL wsdlLocation = CeremonyManagerApi.class.getResource("/ceremony-manager.wsdl");
        CeremonyManagerServiceImplService factory = new CeremonyManagerServiceImplService(wsdlLocation);
        this.ceremony = factory.getCeremonyManagerServiceImplPort();
        String address = "http://" + host + ":" + port + "/teami-polydiploma/webservices/ceremony-manager";
        ((BindingProvider) ceremony).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }
}