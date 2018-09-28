package ru.zhigunov.study.organiser.commands;

import ru.zhigunov.study.organiser.AppContext;

import java.io.PrintStream;

public abstract class OrganiserCommand {
    protected AppContext ctx;

    public OrganiserCommand(AppContext ctx){
        this.ctx = ctx;
        this.ctx.registerCommand(this);
    }

    public AppContext getContext(){
        return ctx;
    }

    public abstract String getName();

    public abstract String help();

    public abstract void execute(String[] args, PrintStream out) throws Exception;

}
