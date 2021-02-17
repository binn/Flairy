package me.puff.flairy.module.modules;

import com.google.common.eventbus.Subscribe;
import me.puff.flairy.event.events.EventArmorStandEntity;
import me.puff.flairy.module.Module;
import me.puff.flairy.utils.WorldRenderUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public class ESPFairySoul extends Module {

    @Override
    public String getName() {
        return "soul";
    }

    @Override
    public String getDescription() {
        return "Fairy Soul Esp";
    }

    @Subscribe
    public void onRender(EventArmorStandEntity event) {
        if (!isEnabled())
            return;

        ItemStack head = event.entity.getEquippedStack(EquipmentSlot.HEAD);

        if (head.hasTag() && head.getItem() instanceof SkullItem) {
            CompoundTag skullOwner = head.getSubTag("SkullOwner");
            CompoundTag properties = skullOwner.getCompound("Properties");
            ListTag textures = properties.getList("textures", 10); // 10 is CompoundTag.getType()
            CompoundTag texture = textures.getCompound(0);
            String value = texture.getString("Value");
            // this is the fairy soul texture
            if (value.equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk2OTIzYWQyNDczMTAwMDdmNmFlNWQzMjZkODQ3YWQ1Mzg2NGNmMTZjMzU2NWExODFkYzhlNmIyMGJlMjM4NyJ9fX0=")) {
                //FlairyLogger.info("Found fairy soul at " + entity.getBlockPos().toShortString());
                float yOffset = event.entity.getHeight() + 0.5f;
                WorldRenderUtils.drawText("Soul", event.entity.getX(), event.entity.getY() + yOffset, event.entity.getZ(), Math.max(2 * (mc.cameraEntity.distanceTo(event.entity) / 12), 1), -10 ,228863);
            }
        }
    }

}
