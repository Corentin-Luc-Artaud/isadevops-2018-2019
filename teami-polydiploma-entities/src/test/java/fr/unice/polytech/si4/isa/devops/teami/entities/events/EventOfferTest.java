package fr.unice.polytech.si4.isa.devops.teami.entities.events;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class EventOfferTest extends AbstractDeployment {

    @Test
    public void EventOfferPersistence() {
        EventOffer eventOffer = new EventOffer();
        entityManager.persist(eventOffer);

        EventOffer eventOffer1 = new EventOffer(LocalDateTime.now(), LocalDateTime.now());
        Club club = new Club("Hello", 10);
        entityManager.persist(club);
        eventOffer1.applyToOffer(club);
        entityManager.persist(eventOffer1);

        EventOffer eventOffer2 = new EventOffer(LocalDateTime.now(), LocalDateTime.now());
        Club club1 = new Club("AAA", 50);
        entityManager.persist(club1);
        eventOffer2.applyToOffer(club1);
        entityManager.persist(eventOffer2);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EventOffer> q = cb.createQuery(EventOffer.class);
        Root<EventOffer> c = q.from(EventOffer.class);
        ParameterExpression<Integer> p = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("id"), p));

        TypedQuery<EventOffer> query = entityManager.createQuery(q);
        query.setParameter(p, 0);
        assertEquals(eventOffer, query.getSingleResult());

        query = entityManager.createQuery(q);
        query.setParameter(p, 1);
        assertEquals(eventOffer1, query.getSingleResult());

        query = entityManager.createQuery(q);
        query.setParameter(p, 2);
        assertEquals(eventOffer2, query.getSingleResult());

    }
}
