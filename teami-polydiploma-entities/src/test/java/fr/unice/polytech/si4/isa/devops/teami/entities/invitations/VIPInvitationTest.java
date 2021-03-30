package fr.unice.polytech.si4.isa.devops.teami.entities.invitations;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class VIPInvitationTest extends AbstractDeployment {
    
    @Test
    public void VIPInvitationPersistence() {
        Speciality speciality = new Speciality("vipinvitation speciality");
        entityManager.persist(speciality);
        Vip vip = new Vip("f", "l", "@",
                entityManager.find(Speciality.class, "vipinvitation speciality"));
        entityManager.persist(vip);
        VIPInvitation vipInvitation = new VIPInvitation(entityManager.find(Vip.class, 0));
        entityManager.persist(vipInvitation);

        assertEquals(vipInvitation, entityManager.find(VIPInvitation.class, 0));
    }
}
