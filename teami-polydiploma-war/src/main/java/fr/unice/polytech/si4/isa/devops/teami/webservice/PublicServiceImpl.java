package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.ceremony.manager.CeremonyManager;
import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Ceremony;
import fr.unice.polytech.si4.isa.devops.teami.utils.PlanningManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "www.polydiploma.fr")
@Stateless(name = "publicService")
public class PublicServiceImpl implements PublicService {

    @EJB
    private CeremonyManager ceremonyManager;

    @EJB
    private PlanningManager planningManager;

    @Override
    public String getCurrentCeremony() {
        Ceremony ceremony = ceremonyManager.getCurrentCeremony();
        if (ceremony == null) {
            return "There is no ceremony yet";
        }
        return ceremony.toString();
    }

    @Override
    public String displayPlanning() {
        return planningManager.displayPlanning();
    }

    @Override
    public String ping() {
        return "pong";
    }
}
