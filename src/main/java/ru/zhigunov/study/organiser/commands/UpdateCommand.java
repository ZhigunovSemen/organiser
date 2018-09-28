package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.AppContext;

import java.io.PrintStream;

public class UpdateCommand extends OrganiserCommand {

    public static final String NAME = "update";
    public static final String DESCRIPTION = "update record";

    public UpdateCommand(AppContext ctx) {
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
