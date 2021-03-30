package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.AdminInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class GetCandidatesForOffer extends Command<AdminInterludeApi> {

    private int indexOffer;

    @Override
    public String identifier() {
        return "candidates";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(this.shell.adminInterludeApi.adminInterludeSOAP.getCandidatesListForOffer(indexOffer));
    }

    @Override
    public String describe() {
        return "candidates <indexOffer>";
    }

    @Override
    public void load(List<String> args) {
        indexOffer = Integer.parseInt(args.get(0));
    }

}