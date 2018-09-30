package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class ExitCommand extends OrganiserCommand {

    private static final String NAME = "exit";
    private static final String DESCRIPTION = "exit from app";

    public ExitCommand(Organiser ctx) {
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
        ctx.need_close();
        out.println("Bye!");
    }

    @Override
    public void postMessage(PrintStream out) { }

}
