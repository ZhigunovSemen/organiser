package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.AppContext;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;

public class HelpCommand extends OrganiserCommand {

    private static final String NAME = "help";
    private static final String DESCRIPTION = "displays help";

    public HelpCommand(AppContext ctx) {
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
    public void execute(String[] args, PrintStream out) {
        Map<String, OrganiserCommand> cmdMap = ctx.getCommandMap();
        Collection<OrganiserCommand> commands = cmdMap.values();

        for (OrganiserCommand cmd : commands){
            out.println(cmd.getName()+" - " + cmd.help());
        }
        out.flush();
    }

}
