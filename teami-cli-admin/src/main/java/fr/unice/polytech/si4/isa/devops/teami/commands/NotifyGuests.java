package fr.unice.polytech.si4.isa.devops.teami.commands;


import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class NotifyGuests extends Command<CeremonyManagerApi> {

    @Override
    public String identifier() {
        return "notifyGuests";
    }

    @Override
    public void execute() throws Exception {
        shell.ceremonyManagerApi.ceremony.notifyGuests();
    }

    @Override
    public String describe() {
        return "notify all guests";
    }

}