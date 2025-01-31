package com.s0cl.util.render.skija

import com.mojang.blaze3d.systems.RenderSystem
import io.github.humbleui.skija.ColorType
import io.github.humbleui.skija.DirectContext
import io.github.humbleui.skija.Image
import io.github.humbleui.skija.SurfaceOrigin
import io.github.humbleui.types.IRect
import net.minecraft.client.MinecraftClient
import org.lwjgl.opengl.GL11

/**
 * @author yuxiangll
 * @since 2025/1/31 下午9:00
 * IntelliJ IDEA
 */
object ImageHelper {

    private val mc = MinecraftClient.getInstance()

    // Use a thread-safe map if this code might be accessed from multiple threads
    private val textures = mutableMapOf<Int, Image>()

    fun getMinecraftAsImage(
        context: DirectContext,
        tex: Int,
        width: Int,
        height: Int,
        origin: SurfaceOrigin = SurfaceOrigin.BOTTOM_LEFT,
        alpha: Boolean = true
    ): Image {

        // Bind the texture to ensure it's active
        RenderSystem.bindTexture(tex)

        // Check if the cached image exists and matches the requested dimensions
        val cachedImage = textures[tex]
        if (cachedImage != null && cachedImage.width == width && cachedImage.height == height) {
            return cachedImage
        }

        // Create a new image if the cached one doesn't exist or has mismatched dimensions
        val newImage = Image.adoptTextureFrom(
            context,
            mc.framebuffer.colorAttachment,
            GL11.GL_TEXTURE_2D,
            width,
            height,
            GL11.GL_RGBA8,
            origin,
            if (alpha) ColorType.RGBA_8888 else ColorType.RGB_888X
        )

        // Update the cache with the new image
        textures[tex] = newImage
        return newImage
    }

//    fun cropImage(image: Image, x: Int, y: Int, width: Int, height: Int): Image? {
//        // 定义剪切区域
//        val cropRect = IRect.makeXYWH(x, y, width, height)
//
//        // 检查剪切区域是否在图像范围内
//        if (cropRect.left < 0 || cropRect.top < 0 ||
//            cropRect.right > image.width || cropRect.bottom > image.height) {
//            throw IllegalArgumentException("Crop region is out of image bounds")
//        }
//
//        // 剪切图像
//        //image.peekPixels()
//        return image.makeSubset(cropRect)
//    }





}