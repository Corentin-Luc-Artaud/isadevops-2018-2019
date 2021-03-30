package fr.unice.polytech.si4.isa.devops.teami.framework;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicCliApi;
import fr.unice.polytech.si4.isa.devops.teami.api.PublicInterludeApi;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Shell {

    private static final String HELP_SYMBOL = "?";
    public PublicCliApi publicCliApi;
    public PublicInterludeApi publicInterludeApi;

    protected String invite;
    private Map<String, Class<? extends Command>> commands = new HashMap<>();

    public final void run() {
        System.out.println("Interactive shell started. " + HELP_SYMBOL + " for help.\n");
        run(System.in, false, 0);
    }

    public void run(InputStream stream, boolean shouldEcho, int indent) {
        Scanner scanner = new Scanner(stream);
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.flush();
            for (int i = 0; i < indent; i++) {
                System.out.print(" ");
            }
            System.out.print(invite + " > ");

            if (!scanner.hasNext()) {
                System.out.println("Reaching end of file");
                break;
            }

            String keyword = scanner.next().trim();

            String rawArgs;
            List<String> args;


            if (scanner.hasNextLine()) {
                rawArgs = scanner.nextLine();
                args = Arrays.asList(rawArgs.split(" "))
                        .stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
            } else {
                rawArgs = "";
                args = new LinkedList<>();
            }

            if (shouldEcho) {
                System.out.println(keyword + " " + rawArgs);
            }
            if (keyword.equals(HELP_SYMBOL)) {
                help();
            } else {
                try {
                    if (keyword.startsWith("#") || keyword.equals(""))
                        shouldContinue = true;
                    else
                        shouldContinue = processCommand(keyword, args);
                } catch (IllegalArgumentException iae) {
                    System.err.println("Illegal arguments for command " + keyword + ": " + args);
                } catch (Exception e) {
                    System.err.println("Exception caught while processing command:\n  " + e);
                }
            }
        }
    }

    private boolean processCommand(String keyword, List<String> args) throws Exception {
        if (!commands.containsKey(keyword)) {
            System.out.println("Unknown command: " + keyword);
            return true;
        }

        Class<? extends Command> command = commands.get(keyword);
        try {
            Command inst = command.newInstance();
            inst.withShell(this);
            return inst.process(args);

        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Unable to instantiate command " + command.toString());
            e.printStackTrace();
            return true;
        }
    }

    @SafeVarargs
    public final void register(Class<? extends Command>... commands) {
        for (Class<? extends Command> c : commands) {
            registerCommand(c);
        }
    }

    private Collection<Class<? extends Command>> instructions() {
        return commands.values();
    }

    private void registerCommand(Class<? extends Command> command) {
        try {
            String identifier = command.newInstance().identifier();
            if (identifier.contains(" "))
                throw new IllegalArgumentException("Identifier cannot contain whitespace");
            commands.put(identifier, command);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            System.err.println("Unable to register command " + command.toString());
            e.printStackTrace();
        }
    }

    private void help() {
        List<Class<? extends Command>> avail = new ArrayList<>(instructions());
        avail.sort(Comparator.comparing(Class::getCanonicalName));
        for (Class<? extends Command> c : avail) {
            try {
                Command instance = c.newInstance();
                System.out.println("  - " + instance.identifier() + ": " + instance.describe());
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println("Unable to print help for registered command " + c);
                e.printStackTrace();
            }
        }
    }
}
