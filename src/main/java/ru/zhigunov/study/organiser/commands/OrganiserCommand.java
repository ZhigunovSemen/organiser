package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.Organiser;

import java.io.PrintStream;

public abstract class OrganiserCommand {
    protected Organiser ctx;

    public OrganiserCommand(Organiser ctx){
        this.ctx = ctx;
        this.ctx.registerCommand(this);
    }

    public Organiser getContext(){
        return ctx;
    }

    public abstract String getName();

    public abstract String help();

    public abstract void execute(String[] args, PrintStream out) throws Exception;

}
