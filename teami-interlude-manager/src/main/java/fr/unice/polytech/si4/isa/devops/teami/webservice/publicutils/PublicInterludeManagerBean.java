package fr.unice.polytech.si4.isa.devops.teami.webservice.publicutils;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PublicInterludeManagerBean implements PublicInterludeManager{

    @PersistenceContext
    private EntityManager entityManager;

    public void postulateOffer(int indexOffer, String clubName) {
        Club club = entityManager.find(Club.class, clubName);
        entityManager.find(EventOffer.class, indexOffer).applyToOffer(club);
    }

    public void registerClub(String clubName, int nbMembers){
        Club club = new Club(clubName, nbMembers);
        entityManager.persist(club);
    }
}
