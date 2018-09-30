package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class OrganiserCommand {
    protected Organiser ctx;

    public OrganiserCommand(Organiser ctx){
        this.ctx = ctx;
        this.ctx.registerCommand(this);
    }

    public abstract String getName();

    public abstract String help();

    public abstract void execute(String[] args, Scanner scanner, PrintStream out) throws Exception;

    public void postMessage(PrintStream out) {
        out.println("\tPress 'help' to see full list of commands, 'exit' to close application.");
    }

    protected static String scanForText(Scanner scanner) {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return "";
    }

}
