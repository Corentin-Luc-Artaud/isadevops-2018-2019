package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;
import fr.unice.polytech.si4.isa.devops.teami.webservice.admin.AdminInterludeManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;
import java.util.Set;

@WebService(targetNamespace = "www.polydiploma.fr")
@Stateless(name = "admin-interlude")
public class AdminInterludeSOAPImpl implements AdminInterludeSOAP {

    @EJB
    private AdminInterludeManager adminInterludeManager;

    @Override
    public void createOffer(String startTimeString, int duration) {
        adminInterludeManager.createOffer(startTimeString, duration);
    }

    @Override
    public String getOffers() {
        return adminInterludeManager.getOffers();
    }

    @Override
    public String getCandidatesListForOffer(int index) {
        return adminInterludeManager.getCandidatesListForOffer(index);
    }

    @Override
    public void chooseCandidateForOffer(int index, String clubName) {
        adminInterludeManager.chooseCandidateForOffer(index, clubName);
    }
}
