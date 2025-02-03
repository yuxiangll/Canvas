package com.s0cl.util.render.skija

import com.mojang.blaze3d.systems.RenderSystem
import io.github.humbleui.skija.*
import net.minecraft.client.MinecraftClient
import org.lwjgl.opengl.GL11

/**
 * @author yuxiangll
 * @since 2025/1/31 下午8:16
 * IntelliJ IDEA
 */
object SkijaHelper {


    private val mc = MinecraftClient.getInstance()

    var context: DirectContext? = null

    var renderTarget: BackendRenderTarget? = null

    var surface: Surface? = null

    var canvas: Canvas? = null

    var dpi = 1f


    fun initSkija(){
        if (context == null){
            context = DirectContext.makeGL()
        }


        surface?.close()
        renderTarget?.close()

        renderTarget = BackendRenderTarget.makeGL(
            (mc.window.framebufferWidth * dpi).toInt(),
            (mc.window.framebufferHeight * dpi).toInt(),
            0,
            8,
            0,
            FramebufferFormat.GR_GL_RGBA8
        )

        surface = Surface.wrapBackendRenderTarget(
            context!!, renderTarget!!, SurfaceOrigin.BOTTOM_LEFT, SurfaceColorFormat.RGBA_8888, ColorSpace.getSRGB()
        )

        canvas = surface!!.canvas


    }





}