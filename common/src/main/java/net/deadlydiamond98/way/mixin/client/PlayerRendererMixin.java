package net.deadlydiamond98.way.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.deadlydiamond98.way.client.WayKeybindings;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Inject(method = "renderNameTag(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/network/chat/Component;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), cancellable = true)
    private void way$renderNameTag(AbstractClientPlayer $$0, Component $$1, PoseStack $$2, MultiBufferSource $$3, int $$4, CallbackInfo ci) {
        if (WayKeybindings.renderNameOverlay()) {
            ci.cancel();
        }
    }
}
