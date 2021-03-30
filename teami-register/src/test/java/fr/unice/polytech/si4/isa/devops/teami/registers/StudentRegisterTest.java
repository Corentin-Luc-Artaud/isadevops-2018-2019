package fr.unice.polytech.si4.isa.devops.teami.registers;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
import fr.unice.polytech.si4.isa.devops.teami.entities.invitations.StudentInvitation;
import fr.unice.polytech.si4.isa.devops.teami.entities.invitations.VIPInvitation;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import fr.unice.polytech.si4.isa.devops.teami.registers.BankMock.BankService;
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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StudentRegisterTest {

    @EJB
    private StudentRegister studentRegister;

    @PersistenceContext
    private EntityManager entityManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StudentRegister.class.getPackage())
                .addPackage(GuestManager.class.getPackage())
                .addPackage(BankService.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Before
    public void init() {
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);
        Student student = new Student("Jean", "Bon", "jambon@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 0);
        Student student1 = new Student("Jeanne", "Dark", "jeannedark@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 1);
        Student student2 = new Student("test", "test", "jeannaedark@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 2);

        Vip vip = new Vip("bob", "moral", "email", entityManager.find(Speciality.class, "SI"));
        entityManager.persist(student);
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(vip);
        entityManager.persist(new VIPInvitation(vip));
        entityManager.persist(new StudentInvitation(student));
        entityManager.persist(new StudentInvitation(student1));
        entityManager.persist(new StudentInvitation(student2));
        studentRegister.registerStudent(1);
        studentRegister.registerStudent(2);
        assertTrue(studentRegister.addAttendant(2, "test", "test"));
        assertTrue(studentRegister.addAttendant(2, "test2", "test2"));
        assertTrue(studentRegister.addAttendant(2, "test3", "test3"));
    }

    @Test
    public void registerStudentTest() {
        assertTrue(studentRegister.registerStudent(0));
        assertFalse(studentRegister.registerStudent(0));
    }

    @Test
    public void addAttendantTest() {
        assertTrue(studentRegister.addAttendant(1, "test", "test"));
        assertTrue(studentRegister.addAttendant(1, "test2", "test2"));
        assertTrue(studentRegister.addAttendant(1, "test3", "test3"));
    }

    @Test
    public void payForAttendantTest() {
        assertEquals(0, studentRegister.getPrice(0), 0);
        assertEquals(8, studentRegister.getPrice(2), 0);
        assertTrue(studentRegister.payForAttendant(2, 1234));
        assertFalse(studentRegister.payForAttendant(0, 1234));
        assertEquals(0, studentRegister.getPrice(2), 0);
    }

    @Test
    public void missingVIP() {
        assertTrue(studentRegister.vipIsMissing("bob", "moral"));
        assertFalse(studentRegister.vipIsMissing("bob", "moral"));
    }

}