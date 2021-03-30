
package fr.unice.polytech.si4.isa.devops.teami.webservice.admin;

import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Planning;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.ClubEvent;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Stateless
public class AdminInterludeManagerBean implements AdminInterludeManager {

    @PersistenceContext
    private EntityManager entityManager;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm");

    public void createOffer(String startTimeString, int duration) {
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        EventOffer eventOffer = new EventOffer(startTime, duration);
        entityManager.persist(eventOffer);
    }

    public String getOffers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EventOffer> q = cb.createQuery(EventOffer.class);
        Root<EventOffer> c = q.from(EventOffer.class);
        q.select(c);
        TypedQuery<EventOffer> query = entityManager.createQuery(q);

        StringBuilder offersString = new StringBuilder();
        for (EventOffer eventOffer : query.getResultList()) {
            offersString.append(eventOffer.toString()).append("\n");
        }
        return offersString.toString();
    }

    public String getCandidatesListForOffer(int index) {
        StringBuilder str = new StringBuilder();
        for (Club club : entityManager.find(EventOffer.class, index).getCandidates()) {
            str.append(club.toString());
        }
        return str.toString();
    }

    public void chooseCandidateForOffer(int index, String clubName) {
        EventOffer eventOffer = entityManager.find(EventOffer.class, index);
        for (Club club : eventOffer.getCandidates()) {
            if (club.getName().equals(clubName)) {
                Planning planning = entityManager.find(Planning.class, 0);
                ClubEvent clubEvent = new ClubEvent(eventOffer.getStartTime(), eventOffer.getEndTime(), club);
                planning.addEvent(clubEvent);
                entityManager.merge(planning);
            }
        }
    }
}
