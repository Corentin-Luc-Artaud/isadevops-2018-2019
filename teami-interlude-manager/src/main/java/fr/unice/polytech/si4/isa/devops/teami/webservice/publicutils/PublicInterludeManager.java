package fr.unice.polytech.si4.isa.devops.teami.webservice.publicutils;

import javax.ejb.Local;

@Local
public interface PublicInterludeManager {

    void postulateOffer(int indexOffer, String clubName);

    void registerClub(String clubName, int nbMembers);

}
