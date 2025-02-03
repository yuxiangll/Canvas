package com.s0cl.module.imp.render

import com.mojang.blaze3d.systems.RenderSystem
import com.s0cl.events.EventRenderGameOverlay
import com.s0cl.events.EventRenderSkija
import com.s0cl.module.Bind
import com.s0cl.module.Category
import com.s0cl.module.Module
import com.s0cl.util.render.gl.render.RenderUtils
import com.s0cl.util.render.gl.state.States
import com.s0cl.util.render.skija.*
import io.github.humbleui.skija.ClipMode
import io.github.humbleui.skija.FilterTileMode
import io.github.humbleui.skija.ImageFilter
import io.github.humbleui.skija.Paint
import io.github.humbleui.types.RRect
import io.github.humbleui.types.Rect
import meteordevelopment.orbit.EventHandler
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Box
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
            //mc.setScreen(ClickGUI)
            //mc.setScreen(ClickScreen)

        }

    }

    override fun onDisable() {
        openned = false
    }


    @EventHandler
    fun draw(event: EventRenderSkija){
        RenderProc.SKIJA_RENDER_QUEUES.addAll(listOf(

            DrawBlurredRect(250f,10f,200f,200f),

            DrawRect(10f,10f,200f,200f)

        ))
    }






}