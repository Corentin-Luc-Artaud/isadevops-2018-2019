package fr.unice.polytech.si4.isa.devops.teami.entities.ceremony;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.Event;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.StudentEvent;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PlanningTest extends AbstractDeployment {

    @Before
    public void init(){
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);
        Event event = new StudentEvent(LocalDateTime.now(), LocalDateTime.now(), entityManager.find(Speciality.class, "SI"));
        entityManager.persist(event);
    }

    @Test
    public void PlanningPersistence() {
        Event event = new StudentEvent(LocalDateTime.now(), LocalDateTime.now(), entityManager.find(Speciality.class, "SI"));
        Planning planning = new Planning();
        planning.addEvent(event);
        entityManager.persist(planning);
        assertEquals(planning, entityManager.find(Planning.class, 0));
    }
}
