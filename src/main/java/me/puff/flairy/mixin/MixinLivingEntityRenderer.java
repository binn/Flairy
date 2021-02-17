package me.puff.flairy.mixin;

import me.puff.flairy.Flairy;
import me.puff.flairy.event.events.EventArmorStandEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class MixinLivingEntityRenderer  {

    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void render(final LivingEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                        int i, CallbackInfo ci) {
        if (entity instanceof ArmorStandEntity) {
            EventArmorStandEntity event = new EventArmorStandEntity((ArmorStandEntity)entity);
            Flairy.eventBus.post(event);
            if (event.isCancelled())
                ci.cancel();
        }
    }

}