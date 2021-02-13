package me.puff.flairy.module;

import me.puff.flairy.module.modules.ESPFairySoul;

import java.util.Arrays;
import java.util.List;

public class ModuleManager {

    private static List<Module> modules = Arrays.asList(
            new ESPFairySoul()
    );

    public static List<Module> getModules() {
        return modules;
    }

    public static Module getModuleByName(String name) {
        for (Module m : modules) {
            if (name.equalsIgnoreCase(m.getName()))
                return m;
        }
        return null;
    }

}
