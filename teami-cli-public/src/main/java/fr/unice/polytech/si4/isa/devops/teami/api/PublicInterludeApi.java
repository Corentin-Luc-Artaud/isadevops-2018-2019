package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.polydiploma.PublicInterludeSOAP;
import fr.polydiploma.PublicInterludeSOAPImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class PublicInterludeApi {

    public PublicInterludeSOAP publicInterludeSOAP;

    public PublicInterludeApi(String host, String port) {
        this.initPublicInterludeApi(host, port);
    }

    private void initPublicInterludeApi(String host, String port) {
        URL wsdlLocation = PublicInterludeSOAPImplService.class.getResource("/public-interlude.wsdl");
        PublicInterludeSOAPImplService factory = new PublicInterludeSOAPImplService(wsdlLocation);
        this.publicInterludeSOAP = factory.getPublicInterludeSOAPImplPort();
        String address = "http://" + host + ":" + port + "/teami-polydiploma/webservices/public-interlude";
        ((BindingProvider) publicInterludeSOAP).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);

    }

}
