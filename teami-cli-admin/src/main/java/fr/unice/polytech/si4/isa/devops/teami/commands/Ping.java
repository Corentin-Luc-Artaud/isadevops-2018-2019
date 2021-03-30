package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class Ping extends Command<CeremonyManagerApi> {

    @Override
    public String identifier() {
        return "ping";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(shell.ceremonyManagerApi.ceremony.ping());
    }

    @Override
    public String describe() {
        return "ping";
    }

}