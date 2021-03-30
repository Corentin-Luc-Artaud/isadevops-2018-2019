package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;
import fr.unice.polytech.si4.isa.devops.teami.webservice.admin.AdminInterludeManager;
import fr.unice.polytech.si4.isa.devops.teami.webservice.publicutils.PublicInterludeManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@WebService(targetNamespace = "www.polydiploma.fr")
@Stateless(name = "public-interlude")
public class PublicInterludeSOAPImpl implements PublicInterludeSOAP {

    @EJB
    PublicInterludeManager publicInterludeManager;

    @EJB
    AdminInterludeManager adminInterludeManager;

    @Override
    public void postulateOffer(int indexOffer,String clubName) {
        publicInterludeManager.postulateOffer(indexOffer, clubName);
    }

    public String getOffers(){
        return adminInterludeManager.getOffers(); //getOffers est déjà dans admininterludemanager donc je l'ai réutilisé. On pourrait rassembler les fonctions communes dans une autre classe si il y en a d'autres
    }

    @Override
    public void registerClub(String clubName, int nbMembers) {
        publicInterludeManager.registerClub(clubName, nbMembers);
    }

}
