package me.puff.flairy.command.commands;

import me.puff.flairy.command.Command;
import me.puff.flairy.module.Module;
import me.puff.flairy.module.ModuleManager;
import me.puff.flairy.utils.FlairyLogger;
import me.puff.flairy.utils.FlairyQueue;

import java.util.Arrays;
import java.util.List;;

public class CmdToggle extends Command {

    @Override
    public List<String> getAliases() {
        return Arrays.asList(
                "toggle",
                "t"
        );
    }

    @Override
    public String getDescription() {
        return "Toggles a module";
    }

    @Override
    public void executeCommand(String command, String[] args) throws Exception {
        for (Module m : ModuleManager.getModules()) {
            if (args[0].equalsIgnoreCase(m.getName())) {
                // FlairyQueue.add(m::toggle);
                m.toggle();
                FlairyLogger.info(m.getName() + " is now " + (m.isEnabled() ? "enabled" : "disabled"));
                return;
            }
        }
        FlairyLogger.error("Module \"" + args[0] + "\" not found");
    }
}