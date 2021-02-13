package me.puff.flairy.mixin;

import me.puff.flairy.utils.FlairyQueue;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;

import com.mojang.authlib.GameProfile;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

    public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        try {


            FlairyQueue.nextQueue();
        } catch (Exception e) {}
    }

}
