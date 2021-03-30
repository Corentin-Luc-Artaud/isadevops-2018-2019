package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class GetVips extends Command<CeremonyManagerApi> {


    @Override
    public void execute() {
        System.out.println(this.shell.ceremonyManagerApi.ceremony.getAllVipToString());
    }

    @Override
    public String identifier() {
        return "getvip";
    }

    @Override
    public String describe() {
        return "Get the vip list. getvip";
    }

}