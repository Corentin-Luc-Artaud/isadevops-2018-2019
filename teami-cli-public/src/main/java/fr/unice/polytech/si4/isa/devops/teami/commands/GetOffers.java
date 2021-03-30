package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class GetOffers extends Command<PublicInterludeApi> {

    @Override
    public String identifier() {
        return "getoffers";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(this.shell.publicInterludeApi.publicInterludeSOAP.getOffers());
    }

    @Override
    public String describe() {
        return "Get offers";
    }

    @Override
    public void load(List<String> args) {

    }

}