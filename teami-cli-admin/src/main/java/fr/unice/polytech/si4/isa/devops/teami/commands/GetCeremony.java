package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.polydiploma.Ceremony;
import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class GetCeremony extends Command<CeremonyManagerApi> {

    @Override
    public String identifier() {
        return "getCeremony";
    }

    @Override
    public void execute() throws Exception {

        System.out.println(new CeremonyWrapper(shell.ceremonyManagerApi.ceremony.getCurrentCeremony()).toString());
    }

    @Override
    public String describe() {
        return "get the current ceremony";
    }

    private class CeremonyWrapper {
        private Ceremony ceremony;

        public CeremonyWrapper(Ceremony ceremony) {
            this.ceremony = ceremony;
        }

        @Override
        public String toString() {
            return ceremony.getPlace()+" "+ceremony.getDate();
        }
    }
}