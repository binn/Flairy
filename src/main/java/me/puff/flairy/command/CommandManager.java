package me.puff.flairy.command;

import me.puff.flairy.command.commands.CmdToggle;
import me.puff.flairy.utils.FlairyLogger;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandManager {

    private static List<Command> commands = Arrays.asList(
            new CmdToggle()
    );

    public static List<Command> getCommands() {
        return commands;
    }

    public static void callCommand(String input) {
        String[] split = input.split(" ", -1);
        String command = split[0];
        String args = input.substring(command.length()).trim();
        boolean foundCommand = false;
        for (Command c : getCommands()) {
            for (String alias : c.getAliases()) {
                if (alias.equalsIgnoreCase(command)) {
                    foundCommand = true;
                    try {
                        c.executeCommand(command, args.split(" "));
                    } catch (Exception e) {
                        e.printStackTrace();
                        FlairyLogger.error("Invalid syntax");
                    }
                    return;
                }
            }
            if (foundCommand)
                return;
        }
        FlairyLogger.error("Command not found");
    }

}