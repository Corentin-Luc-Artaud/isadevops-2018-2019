package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicCliApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class Ping extends Command<PublicCliApi> {

    @Override
    public String identifier() {
        return "ping";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(shell.publicCliApi.publicCli.ping());
    }

    @Override
    public String describe() {
        return "ping";
    }

}