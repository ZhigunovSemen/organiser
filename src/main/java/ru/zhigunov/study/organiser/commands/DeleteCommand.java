package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;
import ru.zhigunov.study.organiser.entity.Contact;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DeleteCommand extends OrganiserCommand {

    public static final String NAME = "delete";
    public static final String DESCRIPTION = "delete record";

    public DeleteCommand(Organiser ctx) {
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
        List<Contact> list = ctx.getContactList().getList();
        Integer id;
        out.println("\t enter ID:");
        try {
            id = Integer.parseInt(scanForText(scanner));
            list.removeIf(record -> record.getId().equals(id));
        } catch (NumberFormatException ex) {
            out.println("invalid number (" + ex.getMessage() + "). Cancel command");
            return;
        }
//        if (Objects.isNull(contact)) {
//            out.println(" no such record with ID = " + String.valueOf(id) + "). Cancel command");
//            return;
//        }
        ctx.saveRecords();
        ctx.loadSequence();
//        out.println("\t deleting record " + contact.toString());
        out.println("Record deleted. "); postMessage(out);
    }

}
