package me.puff.flairy;

import com.google.common.eventbus.EventBus;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Flairy implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static EventBus eventBus = new EventBus();

    public static final String MOD_ID = "flairy";
    public static final String MOD_NAME = "Flairy";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");


    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}