package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.api.PrintingServiceMock.PrintingService;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.mock.StudentApiMock;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class LoadStudentsTest {

    @EJB
    private GuestManager guestManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuestManager.class.getPackage())
                .addClass(StudentApiMock.class)
                .addPackage(PrintingService.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Test
    public void getStudentsUsingMockedHyperplanningApi() {
        guestManager.loadStudentsFromHyperplanning(2018);
        List<Student> students = guestManager.getAllStudents();
        assertEquals(Integer.valueOf(100), Integer.valueOf(students.size()));

        List<Student> studentsWithGoodYear = new ArrayList<>(students);
        studentsWithGoodYear.removeIf(s -> s.getGraduatingYear() != 2018);
        assertEquals(Integer.valueOf(100), Integer.valueOf(studentsWithGoodYear.size()));
    }
}
