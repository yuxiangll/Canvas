package com.s0cl.util.render.gl.render

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.render.*
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import java.awt.Color

/**
 * @author yuxiangll
 * @since 2025/2/1 下午2:43
 * IntelliJ IDEA
 */
object RenderUtils {

    fun draw3DBox(matrixStack: MatrixStack, box: Box, color: Color, lineThickness: Float) {
        val entry = matrixStack.peek()
        val matrix4f = entry.positionMatrix

        val tessellator = RenderSystem.renderThreadTesselator()

        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.disableCull()
        RenderSystem.disableDepthTest()

        RenderSystem.setShader(ShaderProgramKeys.POSITION)

        RenderSystem.setShaderColor(
            color.red.toFloat()/255f,
            color.green.toFloat()/255f,
            color.blue.toFloat()/255f,
            color.alpha.toFloat()/255f
        )

        var bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION)
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.minY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.minY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat())

        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat())

        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.minY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.minY.toFloat(), box.minZ.toFloat())

        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.minY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat())

        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.maxX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat())

        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.minY.toFloat(), box.minZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat())
        bufferBuilder.vertex(matrix4f, box.minX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat())
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end())

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)

        RenderSystem.setShader(ShaderProgramKeys.RENDERTYPE_LINES)

        RenderSystem.lineWidth(lineThickness)

        bufferBuilder = tessellator.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES)

        buildLine3d(
            matrixStack, bufferBuilder, box.minX.toFloat(), box.minY.toFloat(), box.minZ.toFloat(), box.maxX.toFloat(),
            box.minY.toFloat(), box.minZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.maxX.toFloat(), box.minY.toFloat(), box.minZ.toFloat(), box.maxX.toFloat(),
            box.minY.toFloat(), box.maxZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.maxX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat(), box.minX.toFloat(),
            box.minY.toFloat(), box.maxZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.minX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat(), box.minX.toFloat(),
            box.minY.toFloat(), box.minZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.minX.toFloat(), box.minY.toFloat(), box.minZ.toFloat(), box.minX.toFloat(),
            box.maxY.toFloat(), box.minZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.maxX.toFloat(), box.minY.toFloat(), box.minZ.toFloat(), box.maxX.toFloat(),
            box.maxY.toFloat(), box.minZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.maxX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat(), box.maxX.toFloat(),
            box.maxY.toFloat(), box.maxZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.minX.toFloat(), box.minY.toFloat(), box.maxZ.toFloat(), box.minX.toFloat(),
            box.maxY.toFloat(), box.maxZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.minX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat(), box.maxX.toFloat(),
            box.maxY.toFloat(), box.minZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.maxX.toFloat(), box.maxY.toFloat(), box.minZ.toFloat(), box.maxX.toFloat(),
            box.maxY.toFloat(), box.maxZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.maxX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat(), box.minX.toFloat(),
            box.maxY.toFloat(), box.maxZ.toFloat(), color
        )
        buildLine3d(
            matrixStack, bufferBuilder, box.minX.toFloat(), box.maxY.toFloat(), box.maxZ.toFloat(), box.minX.toFloat(),
            box.maxY.toFloat(), box.minZ.toFloat(), color
        )

        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end())

        RenderSystem.enableCull()
        RenderSystem.lineWidth(1f)
        RenderSystem.enableDepthTest()
        RenderSystem.disableBlend()
    }

    fun draw3DLines(matrixStack: MatrixStack, points: List<Vec3d>, color: Color, lineThickness: Float) {



        val tessellator = RenderSystem.renderThreadTesselator()

        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.disableCull()
        RenderSystem.disableDepthTest()

        val bufferBuilder: BufferBuilder = tessellator.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES)
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.setShader(ShaderProgramKeys.RENDERTYPE_LINES)
        RenderSystem.lineWidth(lineThickness)
        for (i in points.indices) {
            if (i == points.size-1) break
            buildLine3d(
                matrixStack, bufferBuilder,

                points[i].x.toFloat(),points[i].y.toFloat(),points[i].z.toFloat(),
                points[i+1].x.toFloat(),points[i+1].y.toFloat(),points[i+1].z.toFloat(),
                color
            )
        }

        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end())

        RenderSystem.enableCull()
        RenderSystem.lineWidth(1f)
        RenderSystem.enableDepthTest()
        RenderSystem.disableBlend()
    }

    private fun buildLine3d(
        matrixStack: MatrixStack, bufferBuilder: BufferBuilder, x1: Float, y1: Float, z1: Float,
        x2: Float, y2: Float, z2: Float, color: Color
    ) {
        val entry = matrixStack.peek()
        val matrix4f = entry.positionMatrix

        val normalized = Vec3d((x2 - x1).toDouble(), (y2 - y1).toDouble(), (z2 - z1).toDouble()).normalize()

        val r = color.red.toFloat()/255f
        val g = color.green.toFloat()/255f
        val b = color.blue.toFloat()/255f

        bufferBuilder.vertex(matrix4f, x1, y1, z1).color(r, g, b, 1.0f).normal(
            entry, normalized.x.toFloat(),
            normalized.y.toFloat(), normalized.z.toFloat()
        )
        bufferBuilder.vertex(matrix4f, x2, y2, z2).color(r, g, b, 1.0f).normal(
            entry, normalized.x.toFloat(),
            normalized.y.toFloat(), normalized.z.toFloat()
        )
    }
}