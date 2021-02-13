package me.puff.flairy.module;

import com.google.common.eventbus.Subscribe;
import me.puff.flairy.Flairy;
import net.minecraft.client.MinecraftClient;

import java.lang.reflect.Method;

public abstract class Module {

     protected MinecraftClient mc = MinecraftClient.getInstance();

     protected boolean enabled = false;

     public abstract String getName();

     public abstract String getDescription();

     public boolean isEnabled() {
          return this.enabled;
     }

     public void toggle() {
          this.enabled = !this.enabled;
          if (enabled)
               onEnable();
          else
               onDisable();
     }

     protected void onEnable() {
          for (Method method : getClass().getMethods()) {
               if (method.isAnnotationPresent(Subscribe.class)) {
                    Flairy.eventBus.register(this);
                    break;
               }
          }
     }

     protected void onDisable() {
          try {
               for (Method method : getClass().getMethods()) {
                    if (method.isAnnotationPresent(Subscribe.class)) {
                         Flairy.eventBus.unregister(this);
                         break;
                    }
               }
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
