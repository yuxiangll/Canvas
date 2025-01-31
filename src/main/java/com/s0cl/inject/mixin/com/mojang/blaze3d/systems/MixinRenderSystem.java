package com.s0cl.inject.mixin.com.mojang.blaze3d.systems;

import com.mojang.blaze3d.systems.RenderSystem;
import com.s0cl.Canvas;
import com.s0cl.events.EventRenderSkija;
import net.minecraft.client.util.tracy.TracyFrameCapturer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


/**
 * @author yuxiangll
 * @since 2024/8/9 上午11:06
 * IntelliJ IDEA
 */

@Mixin(RenderSystem.class)
public abstract class MixinRenderSystem {

    @Inject(method = "flipFrame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Tessellator;clear()V", shift = At.Shift.AFTER))
    private static void flipFrame(long window, TracyFrameCapturer capturer, CallbackInfo ci) {
        EventRenderSkija event = new EventRenderSkija();
        Canvas.INSTANCE.getEventBus().post(event);
    }

}
