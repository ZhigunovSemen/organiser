package ru.zhigunov.study.organiser.commands;

import org.springframework.stereotype.Component;
import ru.zhigunov.study.organiser.Organiser;

import java.io.PrintStream;

@Component
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
    public void execute(String[] args, PrintStream out) {

    }

}
