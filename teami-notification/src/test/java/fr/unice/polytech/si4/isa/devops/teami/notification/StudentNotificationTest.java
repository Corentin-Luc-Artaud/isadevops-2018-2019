package fr.unice.polytech.si4.isa.devops.teami.notification;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StudentNotificationTest {

    @EJB private StudentNotification studentNotification;

    @PersistenceContext
    protected EntityManager entityManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StudentNotification.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Before
    public void init() {
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);
        entityManager.persist(new Student("Jean", "Bon", "jambon@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 0));
        entityManager.persist(new Student("Jeanne", "Dark", "jeannedark@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 1));
        entityManager.persist(new Vip("Jean", "Bon", "fromage@gmail.com", entityManager.find(Speciality.class, "SI")));
        entityManager.persist(new Vip("Jeanne", "Dark", "salade@gmail.com", entityManager.find(Speciality.class, "SI")));
    }

    @Test
    public void notifyStudentSendTwoEmailsTest() {
        assertEquals(2, studentNotification.notifyStudents());
    }

    @Test
    public void notifyStudentGoodEmailsTest() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        studentNotification.notifyStudents();

        assertTrue(outContent.toString().contains("jambon@gmail.com"));
        assertTrue(outContent.toString().contains("jeannedark@gmail.com"));

        System.setOut(originalOut);
    }

    @Test
    public void notifyVipSendTwoEmailsTest() {
        assertEquals(2, studentNotification.notifyVips());
    }

    @Test
    public void notifyVipGoodEmailsTest() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        studentNotification.notifyVips();

        assertTrue(outContent.toString().contains("fromage@gmail.com"));
        assertTrue(outContent.toString().contains("salade@gmail.com"));

        System.setOut(originalOut);
    }

    @Test
    public void notifyEverybodyTest() {
        assertEquals(4, studentNotification.notifyEverybody());
    }

}