package me.puff.flairy.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public class FlairyLogger {

    public static void info(String s) {
        try {
            MinecraftClient.getInstance().inGameHud.getChatHud()
                    .addMessage(new LiteralText(getTextPrefix(Formatting.BLUE) + "§9§lINFO: §9" + s));
        } catch (Exception e) {
            System.out.println("[BH] INFO: " + s);
        }
    }

    public static void error(String s) {
        try {
            MinecraftClient.getInstance().inGameHud.getChatHud()
                    .addMessage(new LiteralText(getTextPrefix(Formatting.RED) + "§c§lERROR: §c" + s));
        } catch (Exception e) {
            System.out.println("[BH] ERROR: " + s);
        }
    }

    private static String getTextPrefix(Formatting color) {
        return color + "[§fF§dL§7A§dI§8R§dY" + color + "] ";
    }

}