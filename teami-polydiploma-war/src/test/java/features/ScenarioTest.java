package features;

import arquillian.AbstractTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.ceremony.manager.CeremonyManager;
import fr.unice.polytech.si4.isa.devops.teami.notification.StudentNotification;
import fr.unice.polytech.si4.isa.devops.teami.registers.StudentRegister;
import fr.unice.polytech.si4.isa.devops.teami.utils.PlanningManager;
import fr.unice.polytech.si4.isa.devops.teami.webservice.admin.AdminInterludeManager;
import fr.unice.polytech.si4.isa.devops.teami.webservice.publicutils.PublicInterludeManager;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class ScenarioTest extends AbstractTest {

    @EJB private CeremonyManager ceremonyManager;
    @EJB private StudentRegister studentRegister;
    @EJB private PlanningManager planningManager;
    @EJB private StudentNotification studentNotification;
    @EJB private AdminInterludeManager adminInterludeManager;
    @EJB private PublicInterludeManager publicInterludeManager;
    @EJB private GuestManager guestManager;

    private LocalDateTime time_of_ceremony;

    @Given("^le responsable communication cree une cérémonie depuis la cli admin pour l'année (\\d+)$")
    public void create_new_rdd(int year) {
        ceremonyManager.createNewRdd(year);
    }

    @Given("^le responsable communication choisit la salle (.*) pour le lieu de la cérémonie$")
    public void set_place(String place) {
        ceremonyManager.setPlace(place);
    }

    @Given("^le responsable communication choisit la date (\\d+)/(\\d+)/(\\d+) et l'heure (\\d+)h(\\d+) pour la cérémonie$")
    public void set_date(int day, int month, int year, int hour, int minute) {
        time_of_ceremony = LocalDateTime.of(year, month, day, hour, minute);
        ceremonyManager.setDate(time_of_ceremony);
    }

    @Given("^le responsable communication notifie les invités : étudiants et VIP$")
    public void notify_guests() {
        studentNotification.notifyEverybody();
    }

    @Given("^le responsable communication ajoute le VIP (.*) (.*) de la formation (.*) et d'email (.*)$")
    public void add_vip(String firstName, String lastName, String speciality, String email) {
        guestManager.addVip(firstName, lastName, email, speciality);
    }

    @Given("^le VIP (.*) (.*) prévient de son absence$")
    public void vip_is_missing(String firstName, String lastName) {
        studentRegister.vipIsMissing(firstName, lastName);
    }

    @When("^le planning est genere$")
    public void generate_initial_planning() {
        planningManager.generateInitialPlanning(ceremonyManager.getCurrentCeremony().getDate());
    }

    @When("^un etudiant d'identifiant (\\d+) accepte son invitation sur la cli student$")
    public void test(int id) {
        studentRegister.registerStudent(id);
    }

    @When("^un etudiant d'identifiant (\\d+) ajoute un accompagnateur de nom (.*) et de prenom (.*) sur la cli student$")
    public void add_accompanist(int id, String lastname, String firstname) {
        studentRegister.addAttendant(id, firstname, lastname);
    }

    @When("^un etudiant d'identifiant (\\d+) paye pour ses accompagnateurs avec son RIB (\\d+)$")
    public void pay_for_accompanist(int id, int rib) {
        studentRegister.payForAttendant(id, rib);
    }

    @When("^l'association (.*) repond a l'appel d'offre, avec (\\d+) membres presents$")
    public void association_awser(String name, int number_of_members) {
        publicInterludeManager.registerClub(name, number_of_members);
        publicInterludeManager.postulateOffer(0, name);
    }

    @When("^un humain affiche le planning depuis la cli public$")
    public void display_planning() {
        planningManager.displayPlanning();
    }

    @When("^un humain veut savoir où et quand a lieu la ceremonie$")
    public void get_ceremonie() {
        ceremonyManager.getCurrentCeremony();
    }

    @When("^le responsable communication crée un appel d'offre pour l'interlude a (\\d+)h(\\d+) pendant (\\d+) minutes$")
    public void create_offer(int hour, int minute, int duration) {
        String offer_date = from_1_to_2_digits(time_of_ceremony.getDayOfMonth()) + "-" +
                from_1_to_2_digits(time_of_ceremony.getMonthValue()) + "-" +
                time_of_ceremony.getYear() + ":" +
                from_1_to_2_digits(hour) + ":" +
                from_1_to_2_digits(minute);

        adminInterludeManager.createOffer(offer_date, duration);
    }

    @When("^le responsable communication accepte la candidature (.*) pour l'interlude$")
    public void accept_association(String name) {
        adminInterludeManager.chooseCandidateForOffer(0, name);
    }

    @Then("^la ceremonie a lieu le (\\d+)/(\\d+)/(\\d+) a (\\d+)h(\\d+)$")
    public void check_date(int day, int month, int year, int hour, int minute) {
        assertEquals(LocalDateTime.of(year, month, day, hour, minute),
                ceremonyManager.getCurrentCeremony().getDate());
    }

    @Then("^la salle de la ceremonie s'appelle (.*)$")
    public void check_place(String place) {
        assertEquals(place, ceremonyManager.getCurrentCeremony().getPlace());
    }

    @Then("^l'etudiant (\\d+) a bien paye$")
    public void check_accompanist_true(int id) {
        assertEquals(0, studentRegister.getPrice(id), 0);
    }

    @Then("^l'etudiant (\\d+) n'a pas paye$")
    public void check_accompanist_false(int id) {
        assertNotEquals(0, studentRegister.getPrice(id), 0);
    }


    private String from_1_to_2_digits(int i) {
        if (i<10) return "0" + i;
        return "" + i;
    }
}
