package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class SeeNbOfRegisteredGuests extends Command<StudentRegisterApi> {


    @Override
    public String identifier() {
        return "seenbregisteredguests";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(this.shell.system.studentSOAP.getNumberOfRegisteredGuest());
    }

    @Override
    public String describe() {
        return "seenbregisteredguests";
    }

}
