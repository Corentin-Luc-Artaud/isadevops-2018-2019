package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicCliApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class GetCurrentCeremony extends Command<PublicCliApi> {

    @Override
    public String identifier() {
        return "getCurrentCeremony";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(shell.publicCliApi.publicCli.getCurrentCeremony());
    }

    @Override
    public String describe() {
        return "get the current ceremony";
    }

}