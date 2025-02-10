package net.deadlydiamond98.koalalib.mixin;

import net.deadlydiamond98.koalalib.util.CustomSplashTexts;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.util.perf.Profiler;

import java.util.List;

@Mixin(SplashManager.class)
public abstract class SplashManagerMixin {
    @Shadow
    @Final
    private List<String> splashes;

    @Inject(method = "apply", at = @At("TAIL"))
    private void apply(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        this.splashes.addAll(CustomSplashTexts.NEW_SPLASH_TEXT);
    }
}
