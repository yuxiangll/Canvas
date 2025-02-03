package com.s0cl.util.render.gl.render

import com.mojang.blaze3d.systems.RenderSystem
import com.s0cl.util.IMinecraft
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.Box


/**
 * @author yuxiangll
 * @since 2025/2/1 上午10:49
 * IntelliJ IDEA
 */
object RenderUtil3D : IMinecraft{

    fun drawSolidBox(bb: Box, matrixStack: MatrixStack) {
        val minX = bb.minX.toFloat()
        val minY = bb.minY.toFloat()
        val minZ = bb.minZ.toFloat()
        val maxX = bb.maxX.toFloat()
        val maxY = bb.maxY.toFloat()
        val maxZ = bb.maxZ.toFloat()

        val matrix = matrixStack.peek().positionMatrix
        RenderSystem.setShader(ShaderProgramKeys.POSITION)
        val tessellator = RenderSystem.renderThreadTesselator()
        val bufferBuilder = tessellator
            .begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION)

        bufferBuilder.vertex(matrix, minX, minY, minZ)
        bufferBuilder.vertex(matrix, maxX, minY, minZ)
        bufferBuilder.vertex(matrix, maxX, minY, maxZ)
        bufferBuilder.vertex(matrix, minX, minY, maxZ)

        bufferBuilder.vertex(matrix, minX, maxY, minZ)
        bufferBuilder.vertex(matrix, minX, maxY, maxZ)
        bufferBuilder.vertex(matrix, maxX, maxY, maxZ)
        bufferBuilder.vertex(matrix, maxX, maxY, minZ)

        bufferBuilder.vertex(matrix, minX, minY, minZ)
        bufferBuilder.vertex(matrix, minX, maxY, minZ)
        bufferBuilder.vertex(matrix, maxX, maxY, minZ)
        bufferBuilder.vertex(matrix, maxX, minY, minZ)

        bufferBuilder.vertex(matrix, maxX, minY, minZ)
        bufferBuilder.vertex(matrix, maxX, maxY, minZ)
        bufferBuilder.vertex(matrix, maxX, maxY, maxZ)
        bufferBuilder.vertex(matrix, maxX, minY, maxZ)

        bufferBuilder.vertex(matrix, minX, minY, maxZ)
        bufferBuilder.vertex(matrix, maxX, minY, maxZ)
        bufferBuilder.vertex(matrix, maxX, maxY, maxZ)
        bufferBuilder.vertex(matrix, minX, maxY, maxZ)

        bufferBuilder.vertex(matrix, minX, minY, minZ)
        bufferBuilder.vertex(matrix, minX, minY, maxZ)
        bufferBuilder.vertex(matrix, minX, maxY, maxZ)
        bufferBuilder.vertex(matrix, minX, maxY, minZ)

        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end())
    }
}