package fr.unice.polytech.si4.isa.devops.teami.notification;

import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Planning;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;
import fr.unice.polytech.si4.isa.devops.teami.webservice.admin.AdminInterludeManager;
import fr.unice.polytech.si4.isa.devops.teami.webservice.publicutils.PublicInterludeManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class AdminInterludeTest {

    @PersistenceContext
    protected EntityManager entityManager;

    @EJB
    AdminInterludeManager adminInterludeManager;

    @EJB
    PublicInterludeManager publicInterludeManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdminInterludeManager.class.getPackage())
                .addPackage(PublicInterludeManager.class.getPackage())
                .addPackage(EventOffer.class.getPackage())
                .addPackage(Club.class.getPackage())
                .addPackage(Planning.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }


    @Test
    public void createOfferTest(){
        adminInterludeManager.createOffer("01-01-2001:01:00", 20);
        System.out.println(adminInterludeManager.getOffers());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm");
        LocalDateTime startTime = LocalDateTime.parse("01-01-2001:01:00", formatter);
        EventOffer eventOffer = new EventOffer(startTime, 20);
        assertEquals(eventOffer.toString()+"\n", adminInterludeManager.getOffers());
    }

    @Test
    public void getCandidatesListForOfferTest(){
        Club club = new Club("leclub", 20);
        entityManager.persist(club);
        adminInterludeManager.createOffer("01-01-2001:01:00", 20);
        publicInterludeManager.postulateOffer(0 , "leclub");
        assertEquals(club.toString(), adminInterludeManager.getCandidatesListForOffer(0));
    }

    @Test
    @Ignore
    public void chooseCandidateForOfferTest(){
        Planning planning = new Planning();
        entityManager.persist(planning);
        Club club = new Club("leclub", 20);
        entityManager.persist(club);
        adminInterludeManager.createOffer("01-01-2001:01:00", 20);
        publicInterludeManager.postulateOffer(0 , "leclub");
        adminInterludeManager.chooseCandidateForOffer(0, "leclub");
        assertEquals(1, entityManager.find(Planning.class, 0).getEvents().size());
    }
}