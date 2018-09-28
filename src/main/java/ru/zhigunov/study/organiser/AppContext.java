package ru.zhigunov.study.organiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.zhigunov.study.organiser.commands.HelpCommand;
import ru.zhigunov.study.organiser.commands.InsertCommand;
import ru.zhigunov.study.organiser.commands.OrganiserCommand;
import static java.lang.System.*;

import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class AppContext {

    @Autowired
    ApplicationContext context;

    private Scanner scanner;

    private final SortedMap<String, OrganiserCommand> cmdMap = new TreeMap<String, OrganiserCommand>();;

    public AppContext() {
        new HelpCommand(this);
        new InsertCommand(this);
    }

    public Map<String,OrganiserCommand> getCommandMap() {
        return cmdMap;
    }

    public void registerCommand(OrganiserCommand cmd){
        String cmdName = cmd.getName();
        if (cmdMap.containsKey(cmdName)){
            out.println("Reload command " + cmdName);
        }
        cmdMap.put(cmdName, cmd);
    }

    public void run() {
        while (true) {
            scanner = new Scanner(System.in);
            System.out.println(context);
        }
    }
}
