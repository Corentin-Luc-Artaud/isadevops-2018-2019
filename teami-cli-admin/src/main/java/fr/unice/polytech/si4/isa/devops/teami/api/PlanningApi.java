package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.polydiploma.PlanningSOAP;
import fr.polydiploma.PlanningSOAPImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class PlanningApi {
    public PlanningSOAP planning;

    public PlanningApi(String host, String port) {
        this.initPlanning(host, port);
    }

    private void initPlanning(String host, String port) {
        URL wsdlLocation = PlanningApi.class.getResource("/planning.wsdl");

        PlanningSOAPImplService factory = new PlanningSOAPImplService(wsdlLocation);
        this.planning = factory.getPlanningSOAPImplPort();
        String address = "http://" + host + ":" + port + "/teami-polydiploma/webservices/planning";
        ((BindingProvider) planning).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);

    }

}
