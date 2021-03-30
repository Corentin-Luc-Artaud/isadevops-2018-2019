package fr.unice.polytech.si4.isa.devops.teami.entities.events;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class VIPEventTest extends AbstractDeployment {

    @Test
    public void VIPEventPersistence() {
        LocalDateTime startDate = LocalDateTime.of(2018, 2, 1, 12, 30);
        LocalDateTime endDate = LocalDateTime.of(2018, 2, 1, 14, 30);
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);

        Vip vip = new Vip("bob", "moral", "email", entityManager.find(Speciality.class, "SI"));
        entityManager.persist(vip);

        VipEvent vipEvent = new VipEvent(startDate, endDate, entityManager.find(Vip.class, 0));
        entityManager.persist(vipEvent);
        assertEquals(vipEvent, entityManager.find(VipEvent.class, 0));
    }

}
