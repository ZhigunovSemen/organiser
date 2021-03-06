package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class HelpCommand extends OrganiserCommand {

    private static final String NAME = "help";
    private static final String DESCRIPTION = "displays help";

    public HelpCommand(Organiser ctx) {
        super(ctx);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String help() {
        return DESCRIPTION;
    }

    @Override
    public void execute(String[] args, Scanner scanner, PrintStream out) {
        Collection<OrganiserCommand> commands = ctx.getCommandMap().values();

        for (OrganiserCommand cmd : commands){
            out.println(cmd.getName()+" - " + cmd.help());
        }
        postMessage(out);
        out.flush();
    }

    @Override
    public void postMessage(PrintStream out) { }

}
