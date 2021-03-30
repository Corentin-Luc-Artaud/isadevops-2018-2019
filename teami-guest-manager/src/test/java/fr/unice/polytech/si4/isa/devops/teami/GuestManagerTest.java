package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.api.PrintingServiceMock.PrintingService;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import fr.unice.polytech.si4.isa.devops.teami.mock.StudentApiMock;
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

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class GuestManagerTest {

    @EJB
    private GuestManager guestManager;

    @PersistenceContext
    private EntityManager entityManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuestManager.class.getPackage())
                .addClass(StudentApiMock.class)
                .addPackage(PrintingService.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Before
    public void guestManagerEmptyListStudent() {
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);
        Student student = new Student("Jean", "Bon", "jambon@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 0);
        Student student1 = new Student("Jeanne", "Dark", "jeannedark@gmail.com", 2018, entityManager.find(Speciality.class, "SI"), 1);
        entityManager.persist(student);
        entityManager.persist(student1);
        entityManager.persist(student);
        entityManager.persist(student1);
    }

    @Test
    public void guestManagerNoStudentLoadedTest() {
        assertEquals(2, guestManager.getAllStudents().size());
    }

    @Test
    public void guestManagerAddStudentTest() {
        guestManager.addStudent("p", "n", "@", 2018, "SI", 2);

        assertEquals("p", guestManager.getAllStudents().get(2).getFirstName());
    }

    @Test
    public void guestManagerRemoveWrongStudentTest() {
        guestManager.addStudent("p1", "n1", "@1", 2018, "SI", 3);
        guestManager.addStudent("p2", "n2", "@2", 2018, "SI", 4);
        guestManager.removeStudent(0);

        assertEquals(4, guestManager.getAllStudents().size());
    }

    @Test
    public void guestManagerRemoveStudentTest() {
        guestManager.addStudent("p1", "n1", "@1", 2018, "SI", 5);
        guestManager.addStudent("p1", "n1", "@1", 2018, "SI", 6);
        guestManager.removeStudent(0);

        assertEquals("p1", guestManager.getAllStudents().get(3).getFirstName());
    }

    @Test
    public void guestManagerGetStudentBySpeciality() {
        entityManager.persist(new Speciality("MAM"));
        entityManager.persist(new Speciality("BAT"));

        guestManager.addStudent("p1", "n1", "@1", 2018, "SI", 7);
        guestManager.addStudent("p2", "n2", "@2", 2018, "SI", 8);
        guestManager.addStudent("p3", "n3", "@3", 2018, "MAM", 9);
        guestManager.addStudent("p4", "n4", "@4", 2018, "BAT", 10);
        guestManager.addStudent("p5", "n5", "@5", 2018, "SI", 11);
        guestManager.addStudent("p6", "n6", "@6", 2018, "BAT", 12);


        assertEquals(8, guestManager.getAllStudents().size());
        assertEquals(5, guestManager.getStudentsBySpeciality("SI").size());
        assertEquals("p3", guestManager.getStudentsBySpeciality("MAM").get(0).getFirstName());
    }
}
