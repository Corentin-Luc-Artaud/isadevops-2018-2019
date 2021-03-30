package fr.unice.polytech.si4.isa.devops.teami.webservice.admin;

import javax.ejb.Local;

@Local
public interface AdminInterludeManager {

    void createOffer(String startTimeString, int duration);

    String getOffers();

    String getCandidatesListForOffer(int index);

    void chooseCandidateForOffer(int index, String clubName);

}

