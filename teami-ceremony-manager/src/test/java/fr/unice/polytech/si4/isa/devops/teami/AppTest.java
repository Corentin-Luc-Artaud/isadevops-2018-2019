package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.ceremony.manager.CeremonyManager;
import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Ceremony;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import fr.unice.polytech.si4.isa.devops.teami.notification.StudentNotification;
import fr.unice.polytech.si4.isa.devops.teami.utils.PlanningManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class AppTest {

    @EJB
    private CeremonyManager ceremonyManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CeremonyManager.class.getPackage())
                .addPackage(StudentNotification.class.getPackage())
                .addPackage(PlanningManager.class.getPackage())
                .addClass(GuestManager.class)
                .addClass(MockGuestManager.class)
                .addClass(Speciality.class)
                .addClass(Student.class)
                .addClass(Ceremony.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Before
    @Test
    public void ceremonyManagerCreateNewRddTest() {
        ceremonyManager.createNewRdd(2018);
        assertEquals(2018, ceremonyManager.getCurrentCeremony().getGraduatedYear());
    }

    @Test
    public void ceremonyManagerChangeDateTest() {
        ceremonyManager.setDate(LocalDateTime.of(2019, 6, 4, 8, 0));
        assertEquals(2019, ceremonyManager.getCurrentCeremony().getDate().getYear());
        assertEquals(6, ceremonyManager.getCurrentCeremony().getDate().getMonthValue());
        assertEquals(4, ceremonyManager.getCurrentCeremony().getDate().getDayOfMonth());
        assertEquals(8, ceremonyManager.getCurrentCeremony().getDate().getHour());
        assertEquals(0, ceremonyManager.getCurrentCeremony().getDate().getMinute());
    }

    @Test
    public void ceremonyManagerChangePlaceTest() {
        ceremonyManager.setPlace("Outside");
        assertEquals("Outside", ceremonyManager.getCurrentCeremony().getPlace());
    }

    @Test
    public void ceremonyAlreadyCreate() {
        Ceremony ceremony = ceremonyManager.getCurrentCeremony();
        ceremonyManager.setDate(LocalDateTime.of(2019, 6, 4, 8, 0));
        ceremonyManager.setPlace("Centre_des_congrès");

        assertNotNull(ceremony);
        assertEquals(LocalDateTime.of(2019, 6, 4, 8, 0), ceremony.getDate());
        assertEquals("Centre_des_congrès", ceremony.getPlace());
        assertEquals(2018, ceremony.getGraduatedYear());

        ceremonyManager.createNewRdd(2018);

        ceremony = ceremonyManager.getCurrentCeremony();
        assertNotNull(ceremony);
        assertEquals("Centre_des_congrès", ceremony.getPlace());
        assertEquals(LocalDateTime.of(2019, 6, 4, 8, 0), ceremony.getDate());
        assertEquals(2018, ceremony.getGraduatedYear());
    }
}
