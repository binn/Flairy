package me.puff.flairy.event.events;

import me.puff.flairy.event.Event;
import net.minecraft.entity.decoration.ArmorStandEntity;

public class EventArmorStandEntity extends Event {

    public ArmorStandEntity entity;

    public EventArmorStandEntity(ArmorStandEntity entity) {
        this.entity = entity;
    }

}