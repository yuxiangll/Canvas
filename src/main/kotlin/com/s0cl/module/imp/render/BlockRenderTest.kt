package com.s0cl.module.imp.render

import com.s0cl.events.EventMouseButton
import com.s0cl.events.EventRender3D
import com.s0cl.module.Bind
import com.s0cl.module.Category
import com.s0cl.module.Module
import com.s0cl.util.render.gl.render.RenderUtils
import meteordevelopment.orbit.EventHandler
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import org.lwjgl.glfw.GLFW
import java.awt.Color


/**
 * @author yuxiangll
 * @since 2025/2/1 下午2:51
 * IntelliJ IDEA
 */
object BlockRenderTest: Module("BlockRenderTest",Category.RENDER) {

    val blockPosList = arrayListOf<Box>()







    @EventHandler
    fun onclick(event:EventMouseButton){
        if (event.button == 1){
            if (mc.crosshairTarget == null) return
            if (mc.crosshairTarget!!.type !== HitResult.Type.BLOCK) return
            if (mc.crosshairTarget !is BlockHitResult) return
            val blockBox =Box( (mc.crosshairTarget as BlockHitResult).blockPos)

            if (blockBox in blockPosList) {
                return
            }

            blockPosList.add(blockBox)
        }
    }


    @EventHandler
    fun draw(event : EventRender3D){

        blockPosList.forEach {
            RenderUtils.draw3DBox(
                event.matrixStack,
                it,
                Color(0.5f,0.7f,1f,0.2f),
                1.5f
            )
        }

        val points = blockPosList.map {
            Vec3d(
                (it.maxX+it.minX)/2,
                (it.maxY+it.minY)/2,
                (it.maxZ+it.minZ)/2
            )
        }
        if (points.isEmpty() || points.size <=1) return
        RenderUtils.draw3DLines(
            event.matrixStack,
            points,
            Color(1f,1f,1f,0.5f),
            3f)


    }






}