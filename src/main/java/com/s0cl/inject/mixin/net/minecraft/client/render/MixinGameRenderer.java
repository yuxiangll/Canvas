package com.s0cl.inject.mixin.net.minecraft.client.render;



import kotlinx.io.UnsafeIoApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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


    @Unique
    private MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode = Opcodes.GETFIELD, ordinal = 0), method = "renderWorld")
    void render3D(RenderTickCounter tickCounter, CallbackInfo ci) {
        //todo 3D render is so hard
    }
    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode = Opcodes.GETFIELD, ordinal = 0), method = "renderWorld")
    void render3dHook(RenderTickCounter tickCounter, CallbackInfo ci) {

    }

    @Inject(at = @At("HEAD"), method = "render")
    public void preRender(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
    }



}