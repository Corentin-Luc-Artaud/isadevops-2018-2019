package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.AdminInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.api.PlanningApi;
import fr.unice.polytech.si4.isa.devops.teami.commands.*;
import fr.unice.polytech.si4.isa.devops.teami.framework.Shell;

public class Main extends Shell {
    public Main(String host, String port) {
        this.ceremonyManagerApi = new CeremonyManagerApi(host, port);
        this.planningApi = new PlanningApi(host, port);
        this.adminInterludeApi = new AdminInterludeApi(host, port);
        this.invite = "Admin";

        register(Bye.class,
                CreateNewRdd.class,
                NotifyGuests.class,
                SetDate.class,
                Ping.class,
                GetCeremony.class,
                SetPlace.class,
                AddStudentEvent.class,
                DeleteEvent.class,
                DisplayPlanning.class,
                MoveEvent.class,
                CreateOffer.class,
                GetOffers.class,
                ChooseCandidate.class,
                GetCandidatesForOffer.class,
                AddVip.class,
                GetVips.class,
                GenerateInitialPlanning.class);
    }

    public static void main(String[] args) {
        String host = (args.length == 0 ? "localhost" : args[0]);
        String port = (args.length < 2 ? "8080" : args[1]);
        System.out.println("\n\nStarting Polydiploma");
        System.out.println("  - Remote server: " + host);
        System.out.println("  - Port number:   " + port);
        Main main = new Main(host, port);
        main.run();
        System.out.println("Exiting polydiploma admin cli\n\n");
    }
}