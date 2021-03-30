package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.AdminInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class ChooseCandidate extends Command<AdminInterludeApi> {

    private int indexOffer;
    private String clubName;

    @Override
    public String identifier() {
        return "choosecandidate";
    }

    @Override
    public void execute() throws Exception {
        this.shell.adminInterludeApi.adminInterludeSOAP.chooseCandidateForOffer(indexOffer, clubName);
    }

    @Override
    public String describe() {
        return "choosecandidate <indexOffer> <clubName>";
    }

    @Override
    public void load(List<String> args) {
        indexOffer = Integer.parseInt(args.get(0));
        clubName = args.get(1);
    }

}