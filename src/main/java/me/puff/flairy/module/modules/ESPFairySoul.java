package me.puff.flairy.module.modules;

import com.google.common.eventbus.Subscribe;

import me.puff.flairy.event.events.EventWorldRenderEntity;
import me.puff.flairy.module.Module;
import me.puff.flairy.utils.FlairyLogger;

import me.puff.flairy.utils.RenderUtils;
import me.puff.flairy.utils.WorldRenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ESPFairySoul extends Module {

    @Override
    public String getName() {
        return "soulesp";
    }

    @Override
    public String getDescription() {
        return "Fairy Soul Esp";
    }

    @Subscribe
    public void onRender(EventWorldRenderEntity event) {
        if (!isEnabled())
            return;

        for (Entity e : mc.world.getEntities()) {
            if (!event.entity.getType().equals(EntityType.ARMOR_STAND))
                continue;

            ArmorStandEntity entity = (ArmorStandEntity)event.entity;
            ItemStack head = entity.getEquippedStack(EquipmentSlot.HEAD);

            if (head.hasTag() && head.getItem() instanceof SkullItem) {
                CompoundTag skullOwner = head.getSubTag("SkullOwner");
                CompoundTag properties = skullOwner.getCompound("Properties");
                ListTag textures = properties.getList("textures", 10); // 10 is CompoundTag.getType()
                CompoundTag texture = textures.getCompound(0);
                String value = texture.getString("Value");
                // this is the fairy soul texture
                if (value.equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk2OTIzYWQyNDczMTAwMDdmNmFlNWQzMjZkODQ3YWQ1Mzg2NGNmMTZjMzU2NWExODFkYzhlNmIyMGJlMjM4NyJ9fX0=")) {
                    //FlairyLogger.info("Found fairy soul at " + entity.getBlockPos().toShortString());
                    Box boundingBox = entity.getBoundingBox().shrink(0, -1.2, 0).expand(0.1, 0, 0.1).offset(0, 0.1, 0);

                    RenderUtils.drawFilledBox(boundingBox, 0.5F, 0.2F, 1F, 0.7F);
                    WorldRenderUtils.drawText("Soul", boundingBox.maxX, boundingBox.maxY+0.2, boundingBox.maxZ, mc.cameraEntity.distanceTo(entity) / 3.8, 228863);
                }
            }
        }
    }

}
