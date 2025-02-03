package com.s0cl.util.render.skija

import java.awt.Color


/**
 * @author yuxiangll
 * @since 2025/2/2 下午2:31
 * IntelliJ IDEA
 */


open class RenderAction(val needMinecraftImage : Boolean = false)

class DrawRect(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val color: Color = Color.WHITE,
) : RenderAction()

class DrawRoundRect(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val radius: Float = 10f,
    val color: Color = Color.WHITE,
) : RenderAction()

//class DrawCircle(
//    val x: Float,
//    val y: Float,
//    val radius: Float,
//    val color: Color = Color.WHITE,
//) : RenderAction()
//
//class DrawLine(
//    val x1: Float,
//    val y1: Float,
//    val x2: Float,
//    val y2: Float,
//    val thickness: Float,
//    val color: Color = Color.WHITE,
//) : RenderAction()

class DrawBlurredRect(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val blur: Float = 20f,
    val pixel: Float = 20f,
) : RenderAction(true)

class DrawBlurredRoundRect(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val radius: Float = 10f,
    val blur: Float = 20f,
    val pixel: Float = 20f,
) : RenderAction(true)

//class DrawBlurredCircle(
//    val x: Float,
//    val y: Float,
//    val radius: Float = 10f,
//    val blurRadius: Float = 10f,
//) : RenderAction(true)




