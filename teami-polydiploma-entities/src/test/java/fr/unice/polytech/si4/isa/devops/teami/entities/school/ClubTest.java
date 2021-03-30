package fr.unice.polytech.si4.isa.devops.teami.entities.school;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class ClubTest extends AbstractDeployment {

    @Test
    public void ClubPersistence() {
        Club club = new Club("c1", 10);
        entityManager.persist(club);

        assertEquals(club, entityManager.find(Club.class, "c1"));
    }
}
