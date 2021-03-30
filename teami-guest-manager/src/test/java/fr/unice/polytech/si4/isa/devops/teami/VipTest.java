package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.api.PrintingServiceMock.PrintingService;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
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
public class VipTest {

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
    public void vipEmptyListVip() {
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);
        Vip Vip = new Vip("Jean", "Bon", "jambon@gmail.com", entityManager.find(Speciality.class, "SI"));
        Vip Vip1 = new Vip("Jeanne", "Dark", "jeannedark@gmail.com", entityManager.find(Speciality.class, "SI"));
        entityManager.persist(Vip);
        entityManager.persist(Vip1);
    }

    @Test
    public void guestManagerNoVipLoadedTest() {
        assertEquals(2, guestManager.getAllVip().size());
    }

    @Test
    public void guestManagerAddVipTest() {
        guestManager.addVip("p", "n", "@", "SI");
        assertEquals("p", guestManager.getAllVip().get(2).getFirstName());
    }

    @Test
    public void guestManagerRemoveWrongVipTest() {
        System.out.println(guestManager.getAllVipToString());
        guestManager.addVip("p1", "n1", "@1", "SI");
        guestManager.addVip("p2", "n2", "@2", "SI");
        System.out.println(guestManager.getAllVipToString());
        guestManager.removeVip("@1");
        System.out.println(guestManager.getAllVipToString());
        /*
        assertEquals(4, guestManager.getAllVip().size());
        guestManager.addVip("p3", "n3", "a@e.fr", "SI");
        assertEquals(5, guestManager.getAllVip().size());
        guestManager.removeVip("a@e.fr");
        assertEquals(4, guestManager.getAllVip().size());
         */
    }

    @Test
    public void guestManagerRemoveVipTest() {
        guestManager.addVip("p1", "n1", "@1", "SI");
        guestManager.addVip("p1", "n1", "@1", "SI");
        guestManager.removeVip("@1");

        assertEquals("p1", guestManager.getAllVip().get(3).getFirstName());
    }
}
