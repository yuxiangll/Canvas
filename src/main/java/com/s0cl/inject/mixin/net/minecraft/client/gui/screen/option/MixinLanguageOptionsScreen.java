package com.s0cl.inject.mixin.net.minecraft.client.gui.screen.option;

import com.s0cl.managers.LanguageStateManager;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author yuxiangll
 * @since 2024/8/10 上午10:21
 * IntelliJ IDEA
 */

@Mixin(LanguageOptionsScreen.class)
public abstract class MixinLanguageOptionsScreen {

    @Inject(method = "onDone", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;reloadResources()Ljava/util/concurrent/CompletableFuture;", ordinal = 0))
    private void preResourceLoad(CallbackInfo info) {
        LanguageStateManager.INSTANCE.setResourceLoadViaLanguage(true);
    }

    @Inject(method = "onDone", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;reloadResources()Ljava/util/concurrent/CompletableFuture;", ordinal = 0, shift = At.Shift.AFTER))
    private void postResourceLoad(CallbackInfo info) {
        LanguageStateManager.INSTANCE.setResourceLoadViaLanguage(false);
    }







}
