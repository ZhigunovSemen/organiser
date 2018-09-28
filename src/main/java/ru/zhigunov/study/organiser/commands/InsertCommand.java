package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.AppContext;

import java.io.PrintStream;

public class InsertCommand extends OrganiserCommand {

    public static final String NAME = "insert";
    public static final String DESCRIPTION = "insert new record, duplicate id not supported";

    public InsertCommand(AppContext ctx) {
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
