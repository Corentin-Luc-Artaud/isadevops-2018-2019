package fr.unice.polytech.si4.isa.devops.teami.utils;

import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Planning;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.StudentEvent;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PlanningManagerTest {

     @PersistenceContext
     EntityManager entityManager;

    @EJB
    PlanningManager planningManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(Planning.class.getPackage())
                .addPackage(PlanningManager.class.getPackage())
                .addPackage(StudentEvent.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Before
    public void guestManagerEmptyListStudent() {
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);
        Speciality speciality2 = new Speciality("BAT");
        entityManager.persist(speciality2);
        Student student = new Student("Jean", "Bon", "jambon@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 0);
        Student student1 = new Student("Jeanne", "Dark", "jeannedark@gmail.com", 2018, entityManager.find(Speciality.class, "BAT"), 1);
        entityManager.persist(student);
        entityManager.persist(student1);
        entityManager.persist(student);
        entityManager.persist(student1);
        LocalDateTime startDate = LocalDateTime.now();
        planningManager.generateInitialPlanning(startDate);
    }

    @Test
    public void addEventTest() {
        assertEquals(2, planningManager.getEvents().size());
        Speciality speciality = new Speciality("MAM");
        entityManager.persist(speciality);
        StudentEvent event = new StudentEvent(LocalDateTime.now(), LocalDateTime.now().plusHours(1), speciality);
        this.planningManager.addEvent(event);
        assertEquals(3, planningManager.getEvents().size());
    }

    @Test
    public void deleteEvent(){
        assertEquals(2, planningManager.getEvents().size());
        Speciality speciality = new Speciality("GB");
        entityManager.persist(speciality);
        StudentEvent event = new StudentEvent(LocalDateTime.MIN, LocalDateTime.MAX, speciality);
        planningManager.addEvent(event);
        assertEquals(3, planningManager.getEvents().size());
        planningManager.deleteEvent(LocalDateTime.MIN, LocalDateTime.MAX);
        assertEquals(3, planningManager.getEvents().size());
    }

    @Test
    public void generateInitialPlanning() {
        assertEquals(2, planningManager.getEvents().size());
    }
}
