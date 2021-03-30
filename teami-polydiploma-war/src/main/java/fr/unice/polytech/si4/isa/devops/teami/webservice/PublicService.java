package fr.unice.polytech.si4.isa.devops.teami.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "www.polydiploma.fr")
public interface PublicService {

    @WebMethod
    public String getCurrentCeremony();

    @WebMethod
    public String displayPlanning();

    @WebMethod
    public String ping();
}
