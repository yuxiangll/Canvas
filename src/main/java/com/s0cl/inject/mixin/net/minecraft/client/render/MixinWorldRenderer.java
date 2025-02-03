package com.s0cl.inject.mixin.net.minecraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.s0cl.Canvas;
import com.s0cl.events.EventRender3D;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author yuxiangll
 * @since 2025/2/1 下午4:01
 * IntelliJ IDEA
 */
@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {

    @Shadow
    private Frustum frustum;

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/ObjectAllocator;Lnet/minecraft/client/render/RenderTickCounter;ZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;)V", cancellable = false)
    public void render(ObjectAllocator allocator, RenderTickCounter tickCounter, boolean renderBlockOutline,
                       Camera camera, GameRenderer gameRenderer, Matrix4f positionMatrix, Matrix4f projectionMatrix,
                       CallbackInfo ci) {

        // Get old (main) framebuffer
        MinecraftClient mc = gameRenderer.getClient();
        Framebuffer oldBuffer = mc.getFramebuffer();
        oldBuffer.endWrite();

        // Get the GUI frame buffer and begin writing to it.
        Framebuffer frameBuffer = mc.getFramebuffer();
        //frameBuffer.resize(mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight());
        frameBuffer.beginWrite(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ZERO,
                GlStateManager.DstFactor.ONE);
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        MatrixStack matrixStack = new MatrixStack();
        matrixStack.multiplyPositionMatrix(positionMatrix);
        Vec3d camPos = camera.getPos();
        matrixStack.translate(-camPos.x, -camPos.y, -camPos.z);

        EventRender3D renderEvent = new EventRender3D(matrixStack, frustum, tickCounter);
        Canvas.INSTANCE.getEventBus().post(renderEvent);
        //Aoba.getInstance().eventManager.Fire(renderEvent);

        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        frameBuffer.endWrite();
        oldBuffer.beginWrite(false);

        // Write frame buffer to the main framebuffer.
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ZERO,
                GlStateManager.DstFactor.ONE);
        frameBuffer.drawInternal(mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight());
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }




}
