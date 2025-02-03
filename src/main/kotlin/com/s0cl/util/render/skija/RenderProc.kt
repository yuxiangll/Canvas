package com.s0cl.util.render.skija

import com.mojang.blaze3d.systems.RenderSystem
import com.s0cl.module.imp.render.ClickGui.mc
import com.s0cl.util.render.gl.state.States
import io.github.humbleui.skija.*
import io.github.humbleui.types.RRect
import io.github.humbleui.types.Rect
import java.util.*

/**
 * @author yuxiangll
 * @since 2025/2/2 下午2:21
 * IntelliJ IDEA
 */
object RenderProc {

    val SKIJA_RENDER_QUEUES: Queue<RenderAction> = LinkedList()

    fun processRender() {
        while (SKIJA_RENDER_QUEUES.isNotEmpty()) {
            States.push()
            RenderSystem.clearColor(1f, 1f, 1f, 1f)
            SkijaHelper.context!!.resetGLAll()
            SkijaHelper.canvas!!.save()

            val action = SKIJA_RENDER_QUEUES.poll()
            if (action.needMinecraftImage) {
                handleMinecraftImageAction(action)
            } else {
                handleDirectDrawAction(action)
            }

            SkijaHelper.canvas!!.restore()
            SkijaHelper.surface!!.flushAndSubmit()
            States.pop()
        }
    }

    private fun handleMinecraftImageAction(action: RenderAction) {
        val image = ImageHelper.getMinecraftAsImage(
            SkijaHelper.context!!,
            mc.framebuffer.colorAttachment,
            mc.framebuffer.textureWidth,
            mc.framebuffer.textureHeight,
            alpha = false
        )

        val blurPaint = Paint().apply {
            //todo blur程度自定义
            imageFilter = ImageFilter.makeBlur(20f, 20f, FilterTileMode.DECAL)
        }

        when (action) {
            is DrawBlurredRect -> {
                SkijaHelper.canvas!!.clipRect(
                    Rect.makeXYWH(action.x, action.y, action.width, action.height),
                    ClipMode.INTERSECT
                )
                SkijaHelper.canvas!!.drawImage(image, 0f, 0f, blurPaint)
            }
            is DrawBlurredRoundRect -> {
                SkijaHelper.canvas!!.clipRRect(
                    RRect.makeXYWH(action.x, action.y, action.width, action.height, action.radius),
                    ClipMode.INTERSECT
                )
                SkijaHelper.canvas!!.drawImage(image, 0f, 0f, blurPaint)
            }
        }
    }

    private fun handleDirectDrawAction(action: RenderAction) {
        val paint = Paint().apply {
            isAntiAlias = true
        }

        when (action) {
            is DrawRect -> {
                paint.color = action.color.rgb
                SkijaHelper.canvas!!.drawRect(
                    Rect.makeXYWH(action.x, action.y, action.width, action.height),
                    paint
                )
            }
            is DrawRoundRect -> {
                paint.color = action.color.rgb
                SkijaHelper.canvas!!.drawRRect(
                    RRect.makeXYWH(action.x, action.y, action.width, action.height, action.radius),
                    paint
                )
            }
        }
    }
}