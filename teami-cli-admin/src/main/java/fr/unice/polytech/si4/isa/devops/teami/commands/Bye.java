package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class Bye extends Command<CeremonyManagerApi> {

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
