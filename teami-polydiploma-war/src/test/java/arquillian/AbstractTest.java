package arquillian;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.api.PrintingServiceMock.PrintingService;
import fr.unice.polytech.si4.isa.devops.teami.api.utils.StudentAPI;
import fr.unice.polytech.si4.isa.devops.teami.ceremony.manager.CeremonyManager;
import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Ceremony;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.notification.StudentNotification;
import fr.unice.polytech.si4.isa.devops.teami.registers.BankMock.IBankService;
import fr.unice.polytech.si4.isa.devops.teami.registers.StudentRegister;
import fr.unice.polytech.si4.isa.devops.teami.utils.PlanningManager;
import fr.unice.polytech.si4.isa.devops.teami.webservice.PublicService;
import fr.unice.polytech.si4.isa.devops.teami.webservice.StudentSOAP;
import fr.unice.polytech.si4.isa.devops.teami.webservice.admin.AdminInterludeManager;
import fr.unice.polytech.si4.isa.devops.teami.webservice.publicutils.PublicInterludeManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackage(CeremonyManager.class.getPackage())
                .addPackage(StudentNotification.class.getPackage())
                .addPackage(StudentRegister.class.getPackage())
                .addPackage(StudentSOAP.class.getPackage())
                .addPackage(PublicService.class.getPackage())
                .addPackage(GuestManager.class.getPackage())
                .addPackage(PlanningManager.class.getPackage())
                .addPackage(Ceremony.class.getPackage())
                .addPackage(IBankService.class.getPackage())
                .addPackage(AdminInterludeManager.class.getPackage())
                .addPackage(PublicInterludeManager.class.getPackage())
                .addPackage(Student.class.getPackage())
                .addClass(StudentAPI.class)
                .addPackage(PrintingService.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

}
