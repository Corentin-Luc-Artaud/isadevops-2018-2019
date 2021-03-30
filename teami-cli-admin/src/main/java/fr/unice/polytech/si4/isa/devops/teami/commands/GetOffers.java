package fr.unice.polytech.si4.isa.devops.teami.commands;

// import fr.polydiploma.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.api.AdminInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class GetOffers extends Command<AdminInterludeApi> {

    @Override
    public String identifier() {
        return "getoffers";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(this.shell.adminInterludeApi.adminInterludeSOAP.getOffers());
    }

    @Override
    public String describe() {
        return "getoffers";
    }

    @Override
    public void load(List<String> args) {

    }

}