package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;
import ru.zhigunov.study.organiser.entity.Contact;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UpdateCommand extends OrganiserCommand {

    public static final String NAME = "update";
    public static final String DESCRIPTION = "update record";

    public UpdateCommand(Organiser ctx) {
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
        String FIO;
        String jobpost;
        String org;
        String email;
        String phones;
        List<String> phoneLIst;
        Contact contact;

        out.println("\t enter ID:");
        try {
            id = Integer.parseInt(scanForText(scanner));
            contact = list.stream()
                    .filter(record -> record.getId().equals(id))
                    .findAny().orElse(null);
        } catch (NumberFormatException ex) {
            out.println("invalid number (" + ex.getMessage() + "). Cancel command");
            return;
        }
        if (Objects.isNull(contact)) {
            out.println(" no such record with ID = " + String.valueOf(id) + "). Cancel command");
            return;
        }

        out.println("\t updating record " + contact.toString());
        out.println("\t enter full name (FIO):");
        FIO = scanForText(scanner);
        out.println("\t enter job post:");
        jobpost = scanForText(scanner);
        out.println("\t enter organisation:");
        org = scanForText(scanner);
        out.println("\t enter email:");
        email = scanForText(scanner);
        out.println("\t enter phones (you can enter a few through the space):");
        phones = scanForText(scanner);
        phoneLIst = Arrays.asList(phones.split(" "));

        contact.setFIO(FIO);
        contact.setJobPost(jobpost);
        contact.setOrganisation(org);
        contact.setEmail(email);
        contact.setEmail(email);
        contact.setPhones(phoneLIst);

        list.removeIf(record -> record.getId().equals(id));
        list.add(contact);
        ctx.getContactList().setList(list);
        ctx.saveRecords();
        out.println("Record changed. "); postMessage(out);
    }

}
