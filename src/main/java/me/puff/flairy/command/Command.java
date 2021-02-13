package me.puff.flairy.command;

import java.util.List;

public abstract class Command {

    public static String PREFIX = "f!";

    public abstract List<String> getAliases();

    public abstract String getDescription();

    public abstract void executeCommand(String command, String[] args) throws Exception;

}