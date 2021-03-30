package fr.unice.polytech.si4.isa.devops.teami.framework;

import java.util.List;

public abstract class Command<T> {

    protected Shell shell;

    abstract public String identifier();

    abstract public void execute() throws Exception;

    abstract public String describe();

    public boolean shouldContinue() {
        return true;
    }  // default implementation

    public void load(List<String> args) {
    }          // default implementation

    public void withShell(Shell shell) {
        this.shell = shell;
    }

    public boolean process(List<String> args) throws Exception {
        try {
            load(args);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        execute();
        return shouldContinue();
    }

}
