package ru.zhigunov.study.organiser;

import ru.zhigunov.study.organiser.commands.*;
import ru.zhigunov.study.organiser.entity.Contact;
import ru.zhigunov.study.organiser.entity.ContactList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

public class Organiser {

    private static final String INVALID_COMMAND = "Invalid command";
    private static final String HELP_MESSAGE = "Press help to see full list of commands";
    private static final String XML_PATH = "contacts.xml";

    private final SortedMap<String, OrganiserCommand> cmdMap =
            new TreeMap<String, OrganiserCommand>();

    private InputStream in;
    private PrintStream out;

    private ContactList contactList;
    /** current order for id */
    private int sequence;

    private boolean needClose = false;

    public Organiser(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        new HelpCommand(this);
        new InsertCommand(this);
        new UpdateCommand(this);
        new DeleteCommand(this);
        new ListCommand(this);
        new FindCommand(this);
        new ExitCommand(this);
        loadRecords();
    }

    public void registerCommand(OrganiserCommand cmd){
        String cmdName = cmd.getName();
        if (cmdMap.containsKey(cmdName)){
            out.println(String.format("  Reload command '%s'...", cmdName));
        } else {
            out.println(String.format("  Load command '%s'...", cmdName));
        }
        cmdMap.put(cmdName, cmd);
    }

    public int getSequence() {
        return ++sequence;
    }
    public void need_close() {
        needClose = true;
    }

    public Map<String,OrganiserCommand> getCommandMap() {
        return cmdMap;
    }

    public ContactList getContactList() {
        return contactList;
    }

    private void loadRecords() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            contactList = (ContactList) jaxbUnmarshaller.unmarshal(new File(XML_PATH));
        } catch (JAXBException ex) {
            contactList = null;
        }
        if(Objects.isNull(contactList) || Objects.isNull(contactList.getList())) {
            contactList = new ContactList();
            contactList.setList(new LinkedList<>());
        }
        loadSequence();
    }
    public void loadSequence() {
        List<Contact> contactListList = contactList.getList();
        contactListList.sort(Comparator.comparingInt(Contact::getId));
        sequence = (contactListList.size() > 0)
                ? contactListList.get(contactListList.size() - 1).getId()
                : 1;
    }


    public void saveRecords() {
        try {
            File dataFile = new File(XML_PATH);
            if (!dataFile.exists()) dataFile.createNewFile();
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(contactList, new File(XML_PATH));
        } catch (IOException | JAXBException ex) {
            ex.printStackTrace();
        }
    }


    public void run() {
        out.println("Hi! This is your personal orginiser. There are "
                + contactList.getList().size() + " items in base." );
        out.println(HELP_MESSAGE);
        Scanner scanner = null;
        try {
            scanner = new Scanner(in);
            while (!needClose && scanner.hasNext()) {
                String inputText = scanner.nextLine();
                String[] words = inputText.split(" ");
                String commandName = words[0];
                OrganiserCommand command = cmdMap.get(commandName);
                if (Objects.isNull(command)) {
                    out.println(INVALID_COMMAND + HELP_MESSAGE);
                    continue;
                }
                try {
                    command.execute(words, scanner, out);
                } catch (Exception ex) {
                    out.println("exception: " + ex.getCause() + System.lineSeparator());
                }
            }
        } finally {
            if (Objects.nonNull(scanner)) scanner.close();
        }
    }
}
