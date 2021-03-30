package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Ceremony;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "www.polydiploma.fr")
public interface CeremonyManagerService {

    @WebMethod
    String ping();

    @WebMethod
    void createNewRdd(@WebParam(name = "year") int year);

    @WebMethod
    @WebResult(name = "ceremony")
    Ceremony getCurrentCeremony();

    @WebMethod
    void setDate(@WebParam(name = "date") String date);

    @WebMethod
    void setPlace(@WebParam(name = "place") String place);

    @WebMethod
    void notifyGuests();

    @WebMethod
    void generateInitialPlanning();

    @WebMethod
    void addVip(String firstName, String lastName, String email, String speciality);

    @WebMethod
    void removeVip(String email);

    @WebMethod
    @WebResult(name = "vips")
    String getAllVipToString();


}