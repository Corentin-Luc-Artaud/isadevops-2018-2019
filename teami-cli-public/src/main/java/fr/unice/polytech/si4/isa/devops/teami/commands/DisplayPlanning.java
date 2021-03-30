package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicCliApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class DisplayPlanning extends Command<PublicCliApi> {

    @Override
    public void execute() {
        System.out.println(shell.publicCliApi.publicCli.displayPlanning());
    }

    @Override
    public String identifier() {
        return "displayPlanning";
    }

    @Override
    public String describe() {
        return "Display the planning";
    }

}