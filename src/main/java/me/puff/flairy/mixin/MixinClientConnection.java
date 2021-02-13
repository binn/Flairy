package me.puff.flairy.mixin;

import me.puff.flairy.command.Command;
import me.puff.flairy.command.CommandManager;
import me.puff.flairy.utils.FlairyLogger;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

@Mixin(ClientConnection.class)
public class MixinClientConnection {

    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet_1, GenericFutureListener<? extends Future<? super Void>> genericFutureListener_1, CallbackInfo callback) {
        if (packet_1 instanceof ChatMessageC2SPacket) {
            ChatMessageC2SPacket pack = (ChatMessageC2SPacket) packet_1;
            if (pack.getChatMessage().startsWith(Command.PREFIX)) {
                CommandManager.callCommand(pack.getChatMessage().substring(Command.PREFIX.length()));
                callback.cancel();
            }
        }
    }

}