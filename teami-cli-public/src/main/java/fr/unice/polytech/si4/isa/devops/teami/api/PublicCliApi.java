package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.polydiploma.PublicService;
import fr.polydiploma.PublicServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class PublicCliApi {
    public PublicService publicCli;

    public PublicCliApi(String host, String port) {
        this.initPublicCli(host, port);
    }

    private void initPublicCli(String host, String port) {
        URL wsdlLocation = PublicCliApi.class.getResource("/publicService.wsdl");

        PublicServiceImplService factory = new PublicServiceImplService(wsdlLocation);
        this.publicCli = factory.getPublicServiceImplPort();
        String address = "http://" + host + ":" + port + "/teami-polydiploma/webservices/publicService";
        ((BindingProvider) publicCli).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);

    }

}
