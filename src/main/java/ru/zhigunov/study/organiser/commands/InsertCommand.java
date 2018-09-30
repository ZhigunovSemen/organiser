package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;
import ru.zhigunov.study.organiser.entity.Contact;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InsertCommand extends OrganiserCommand {

    private static final String NAME = "insert";
    private static final String DESCRIPTION = "insert new record, duplicate id not supported";

    public InsertCommand(Organiser ctx) {
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
        String FIO;
        String jobpost;
        String org;
        String email;
        String phones;
        List<String> phoneList;

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
        String[] temp = phones.split(" ");
        phoneList = Arrays.asList(temp);

        Contact contact = new Contact();
        contact.setId(ctx.getSequence());
        contact.setFIO(FIO);
        contact.setJobPost(jobpost);
        contact.setOrganisation(org);
        contact.setEmail(email);
        contact.setPhones(phoneList);

        list.add(contact);
        ctx.getContactList().setList(list);
        ctx.saveRecords();
        out.println("Record added. "); postMessage(out);
    }

}
