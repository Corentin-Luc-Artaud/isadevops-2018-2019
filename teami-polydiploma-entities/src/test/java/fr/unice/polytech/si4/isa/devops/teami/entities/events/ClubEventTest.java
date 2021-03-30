package fr.unice.polytech.si4.isa.devops.teami.entities.events;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class ClubEventTest extends AbstractDeployment {

    @Test
    public void StudentEventPersistence() {
        LocalDateTime startDate = LocalDateTime.of(2018, 2, 1, 12, 30);
        LocalDateTime endDate = LocalDateTime.of(2018, 2, 1, 14, 30);
        Club club = new Club("WeAreLegion", 1);
        entityManager.persist(club);

        ClubEvent clubEvent = new ClubEvent(startDate, endDate, entityManager.find(Club.class, 0));
        entityManager.persist(clubEvent);
        assertEquals(clubEvent, entityManager.find(ClubEvent.class, 0));
    }
}
