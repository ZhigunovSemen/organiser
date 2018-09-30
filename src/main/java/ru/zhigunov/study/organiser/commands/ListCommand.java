package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ListCommand extends OrganiserCommand {

    public static final String NAME = "list";
    public static final String DESCRIPTION = "find <sort field1, sort field2, ...> show records sorting by sort fields ";

    public ListCommand(Organiser ctx) {
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
        ctx.getContactList().getList().forEach(out::println);
        postMessage(out);
    }

}
