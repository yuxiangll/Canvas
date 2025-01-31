package com.s0cl.inject.mixin.net.minecraft.client.util;

import com.s0cl.util.render.skija.SkijaHelper;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author yuxiangll
 * @since 2024/8/9 上午11:07
 * IntelliJ IDEA
 */

@Mixin(Window.class)
public abstract class MixinWindow {

    @Inject(method = "onFramebufferSizeChanged", at = @At("RETURN"))
    private void onFramebufferSizeChanged(long window, int width, int height, CallbackInfo ci) {
        SkijaHelper.INSTANCE.initSkija();

    }



}
