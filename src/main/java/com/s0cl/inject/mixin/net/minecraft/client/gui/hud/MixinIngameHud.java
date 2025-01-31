package com.s0cl.inject.mixin.net.minecraft.client.gui.hud;

import com.s0cl.Canvas;
import com.s0cl.events.EventRenderGameOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author yuxiangll
 * @since 2024/8/9 下午8:08
 * IntelliJ IDEA
 */


@Mixin(InGameHud.class)
public abstract class MixinIngameHud {

    @Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        EventRenderGameOverlay eventRenderGameOverlay = new EventRenderGameOverlay(context, tickCounter);
        Canvas.INSTANCE.getEventBus().post(eventRenderGameOverlay);
        if (eventRenderGameOverlay.isCancelled()) ci.cancel();
    }


}