package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.commands.*;
import fr.unice.polytech.si4.isa.devops.teami.framework.Shell;

public class Main extends Shell<StudentRegisterApi> {
    public Main(String host, String port) {
        this.system = new StudentRegisterApi(host, port);
        this.invite = "Student";

        register(SeeAllStudents.class, SeeRegisteredStudents.class, RegisterStudent.class, Bye.class, AddAttendant.class, GetPrice.class, PayForAttendant.class, SeeNbOfRegisteredGuests.class);
    }

    public static void main(String[] args) {
        String host = (args.length == 0 ? "localhost" : args[0]);
        String port = (args.length < 2 ? "8080" : args[1]);
        System.out.println("  - Remote server: " + host);
        System.out.println("  - Port number:   " + port);
        Main main = new Main(host, port);
        main.run();
        System.out.println("Exiting PolyDiploma\n\n");
    }
}