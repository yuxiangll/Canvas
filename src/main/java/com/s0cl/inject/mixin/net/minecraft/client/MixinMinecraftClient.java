package com.s0cl.inject.mixin.net.minecraft.client;



import com.s0cl.Canvas;
import com.s0cl.events.EventClientTick;
import com.s0cl.events.EventPreAttack;

import com.s0cl.util.render.skija.SkijaHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;




/**
 * @author yuxiangll
 * @since 2024/8/9 下午8:10
 * IntelliJ IDEA
 */

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Shadow @Nullable
    public Screen currentScreen;

    @Shadow
    public abstract void setScreen(@Nullable Screen screen);


    @Shadow public abstract boolean isFinishedLoading();



    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci){
        Canvas.INSTANCE.getEventBus().post(new EventClientTick());
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    void init(RunArgs args, CallbackInfo ci) {
        SkijaHelper.INSTANCE.initSkija();
    }


    @Inject(method = "onFinishedLoading", at = @At("TAIL"))
    private void loadingFinished(CallbackInfo ci) {
        //FontManager.initialize();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    void preTickHook(CallbackInfo ci) {
        Canvas.INSTANCE.getEventBus().post(new EventClientTick());
    }


    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void doAttackHook(CallbackInfoReturnable<Boolean> cir) {
        final EventPreAttack event = new EventPreAttack();
        Canvas.INSTANCE.getEventBus().post(event);
        if (event.isCancelled()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "setOverlay", at = @At("HEAD"), cancellable = true)
    private void setOverlay(Overlay overlay, CallbackInfo ci) {
        if (overlay instanceof SplashOverlay && (currentScreen instanceof LanguageOptionsScreen || currentScreen instanceof CraftingScreen)) ci.cancel();

    }


}