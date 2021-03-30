package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class RemoveVip extends Command<CeremonyManagerApi> {

    private String vipmail;

    @Override
    public void execute() {
        this.shell.ceremonyManagerApi.ceremony.removeVip(vipmail);
    }

    @Override
    public String identifier() {
        return "removevip";
    }

    @Override
    public String describe() {
        return "removevip <vipmail>";
    }

    @Override
    public void load(List<String> args) {
        vipmail = args.get(0);
    }


}