package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class FindCommand extends OrganiserCommand {

    public static final String NAME = "find";
    public static final String DESCRIPTION = "find <phone1 phone2> - fine all record with any of entered phone number";

    public FindCommand(Organiser ctx) {
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
        if (args.length < 2) {
            out.println("should be at least one argument after command: phone. For example: find 123");
            return;
        }
        String phone = args[1];
        ctx.getContactList().getList().stream()
                .filter(contact -> contact.getPhones().contains(phone))
                .forEach(out::println);
        postMessage(out);
    }

}
