package com.s0cl.inject.mixin.net.minecraft.client.render;


import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author yuxiangll
 * @since 2024/8/9 下午8:06
 * IntelliJ IDEA
 */

@Mixin(GameRenderer.class)
@SuppressWarnings("SpellCheckingInspection")
public abstract class MixinGameRenderer {

    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode = Opcodes.GETFIELD, ordinal = 0), method = "renderWorld")
    void renderWorld(RenderTickCounter tickCounter, CallbackInfo ci) {
        //todo all undone
    }


    @Inject(at = @At("HEAD"), method = "render")
    public void preRender(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
    }



}