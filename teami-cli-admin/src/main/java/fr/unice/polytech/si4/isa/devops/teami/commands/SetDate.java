package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SetDate extends Command<CeremonyManagerApi> {

    private LocalDateTime date;

    @Override
    public String identifier() {
        return "set_date";
    }

    @Override
    public void execute() throws Exception {
        shell.ceremonyManagerApi.ceremony.setDate(date.toString());
    }

    @Override
    public String describe() {
        return "set the date of the ceremony the format should be dd-MM-yyy:HH:mm";
    }

    @Override
    public void load(List<String> args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy:HH:mm");
        date = LocalDateTime.parse(args.get(0), formatter);
    }

}