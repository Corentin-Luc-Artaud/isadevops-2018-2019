package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;

import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "www.polydiploma.fr")
public interface PublicInterludeSOAP {

    void postulateOffer(int indexOffer, String clubName);

    String getOffers();

    void registerClub(String clubName, int nbMembers);


}
