package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicCliApi;
import fr.unice.polytech.si4.isa.devops.teami.api.PublicInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.commands.*;
import fr.unice.polytech.si4.isa.devops.teami.framework.Shell;

public class Main extends Shell {
    public Main(String host, String port) {
        this.publicCliApi = new PublicCliApi(host, port);
        this.publicInterludeApi = new PublicInterludeApi(host, port);
        this.invite = "public";

        register(Bye.class,
                Ping.class,
                GetCurrentCeremony.class,
                DisplayPlanning.class, GetOffers.class, PostulateOffer.class, RegisterClub.class);
    }

    public static void main(String[] args) {
        String host = (args.length == 0 ? "localhost" : args[0]);
        String port = (args.length < 2 ? "8080" : args[1]);
        System.out.println("\n\nStarting Polydiploma");
        System.out.println("  - Remote server: " + host);
        System.out.println("  - Port number:   " + port);
        Main main = new Main(host, port);
        main.run();
        System.out.println("Exiting polydiploma public cli\n\n");
    }
}