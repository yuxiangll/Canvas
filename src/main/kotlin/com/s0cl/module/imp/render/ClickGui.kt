package com.s0cl.module.imp.render

import com.mojang.blaze3d.systems.RenderSystem
import com.s0cl.events.EventRenderSkija
import com.s0cl.gui.clickgui.ClickGUI
import com.s0cl.module.Bind
import com.s0cl.module.Category
import com.s0cl.module.Module
import com.s0cl.util.render.gl.state.States
import com.s0cl.util.render.skija.ImageHelper
import com.s0cl.util.render.skija.SkijaHelper
import io.github.humbleui.skija.ClipMode
import io.github.humbleui.skija.FilterTileMode
import io.github.humbleui.skija.ImageFilter
import io.github.humbleui.skija.Paint
import io.github.humbleui.types.Rect
import meteordevelopment.orbit.EventHandler
import org.lwjgl.glfw.GLFW
import java.awt.Color

/**
 * @author yuxiangll
 * @since 2024/7/8 下午12:13
 * IntelliJ IDEA
 */
object ClickGui : Module(
    "ClickGui",
    Category.RENDER,
) {

    init {

        this.bind.value = Bind(GLFW.GLFW_KEY_RIGHT_SHIFT)

    }
    var openned = false

    override fun onEnable() {
        if (!openned){
            openned = true
            //TODO()
            //mc.setScreen(ClickGUI)
            //mc.setScreen(ClickScreen)

        }

    }

    override fun onDisable() {
        openned = false
    }


    @EventHandler
    fun draw(event: EventRenderSkija){
        run {
            States.push()
            RenderSystem.clearColor(0f, 0f, 0f, 0f)
            SkijaHelper.context!!.resetGLAll()
            val textureImage = ImageHelper.getMinecraftAsImage(
                SkijaHelper.context!!,
                mc.framebuffer.colorAttachment,
                mc.framebuffer.textureWidth,
                mc.framebuffer.textureHeight,
                alpha = false
            )
            //textureImage.

            val paint = Paint().apply {
                imageFilter = ImageFilter.makeBlur(20f, 20f, FilterTileMode.CLAMP)
                //color = Color(255,255,255).rgb
            }

            val rect = Rect.makeXYWH(10f, 20f, 200f, 200f)
            SkijaHelper.canvas!!.save()
            SkijaHelper.canvas!!.clipRect(rect, ClipMode.INTERSECT)
            SkijaHelper.canvas!!.drawImage(textureImage, 0f, 0f, paint)
            SkijaHelper.canvas!!.restore()
            SkijaHelper.surface!!.flushAndSubmit()
            States.pop()
        }



        run {
            States.push()
            RenderSystem.clearColor(0f, 0f, 0f, 0f)
            SkijaHelper.context!!.resetGLAll()
            val rect2 = Rect.makeXYWH(10f, 20f, 200f, 200f)
            SkijaHelper.canvas!!.save()
            SkijaHelper.canvas!!.drawRectShadowNoclip(rect2, -2f,-2f,4f,4f,Color(0,0,0,53).rgb)
            SkijaHelper.canvas!!.restore()
            SkijaHelper.surface!!.flushAndSubmit()
            States.pop()
        }






    }






}