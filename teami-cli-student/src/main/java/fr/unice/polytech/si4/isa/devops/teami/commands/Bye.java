package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class Bye extends Command<StudentRegisterApi> {

    @Override
    public String identifier() {
        return "bye";
    }

    @Override
    public void execute() {
    }

    @Override
    public String describe() {
        return "Exiting ....";
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

}
