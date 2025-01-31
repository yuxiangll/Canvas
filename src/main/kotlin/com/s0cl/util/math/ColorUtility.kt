package com.s0cl.util.math

import net.minecraft.util.math.MathHelper
import java.awt.Color
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * @author yuxiangll
 * @since 2024/7/6 上午9:10
 * IntelliJ IDEA
 */
object ColorUtility {
    fun scrollAnimate(endPoint: Float, current: Float, speed: Float): Float {
        var speed = speed
        val shouldContinueAnimation = endPoint > current
        if (speed < 0.0f) {
            speed = 0.0f
        } else if (speed > 1.0f) {
            speed = 1.0f
        }

        val dif = (max(endPoint.toDouble(), current.toDouble()) - min(
            endPoint.toDouble(),
            current.toDouble()
        )).toFloat()
        val factor = dif * speed
        return current + (if (shouldContinueAnimation) factor else -factor)
    }

    fun injectAlpha(color: Color, alpha: Int): Color {
        return Color(color.red, color.green, color.blue, MathHelper.clamp(alpha, 0, 255))
    }

    fun TwoColoreffect(cl1: Color, cl2: Color, speed: Double, count: Double): Color {
        var angle = (((System.currentTimeMillis()) / speed + count) % 360).toInt()
        angle = (if (angle >= 180) 360 - angle else angle) * 2
        return interpolateColorC(cl1, cl2, angle / 360f)
    }

    fun astolfo(clickgui: Boolean, yOffset: Int): Color {
        val speed = (if (clickgui) 35 * 100 else 30 * 100).toFloat()
        var hue = ((System.currentTimeMillis() % speed.toInt()) + yOffset).toFloat()
        if (hue > speed) {
            hue -= speed
        }
        hue /= speed
        if (hue > 0.5f) {
            hue = 0.5f - (hue - 0.5f)
        }
        hue += 0.5f
        return Color.getHSBColor(hue, 0.4f, 1f)
    }

    fun rainbow(delay: Int, saturation: Float, brightness: Float): Color {
        var rainbow = ceil(((System.currentTimeMillis() + delay) / 16f).toDouble())
        rainbow %= 360.0
        return Color.getHSBColor((rainbow / 360).toFloat(), saturation, brightness)
    }

    fun skyRainbow(speed: Int, index: Int): Color {
        var angle = ((System.currentTimeMillis() / speed + index) % 360).toInt()
        return Color.getHSBColor(
            if (((360.also { angle %= it }) / 360.0).toFloat()
                    .toDouble() < 0.5
            ) -((angle / 360.0).toFloat()) else (angle / 360.0).toFloat(), 0.5f, 1.0f
        )
    }

    fun fade(speed: Int, index: Int, color: Color, alpha: Float): Color {
        val hsb = Color.RGBtoHSB(color.red, color.green, color.blue, null)
        var angle = ((System.currentTimeMillis() / speed + index) % 360).toInt()
        angle = (if (angle > 180) 360 - angle else angle) + 180

        val colorHSB = Color(Color.HSBtoRGB(hsb[0], hsb[1], angle / 360f))

        return Color(
            colorHSB.red, colorHSB.green, colorHSB.blue, max(
                0.0, min(
                    255.0,
                    (alpha * 255).toInt().toDouble()
                )
            ).toInt()
        )
    }

    fun getAnalogousColor(color: Color): Color {
        val hsb = Color.RGBtoHSB(color.red, color.green, color.blue, null)
        val degree = 0.84f
        val newHueSubtracted = hsb[0] - degree
        return Color(Color.HSBtoRGB(newHueSubtracted, hsb[1], hsb[2]))
    }

    fun applyOpacity(color: Color, opacity: Float): Color {
        var opacity = opacity
        opacity = min(1.0, max(0.0, opacity.toDouble())).toFloat()
        return Color(color.red, color.green, color.blue, (color.alpha * opacity).toInt())
    }

    fun applyOpacity(color_int: Int, opacity: Float): Int {
        var opacity = opacity
        opacity = min(1.0, max(0.0, opacity.toDouble())).toFloat()
        val color = Color(color_int)
        return Color(color.red, color.green, color.blue, (color.alpha * opacity).toInt()).rgb
    }

    fun darker(color: Color, factor: Float): Color {
        return Color(
            max(
                (color.red * factor).toInt()
                    .toDouble(), 0.0
            ).toInt()
            , max(
                (color.green * factor).toInt()
                    .toDouble(), 0.0
            ).toInt(), max((color.blue * factor).toInt().toDouble(), 0.0).toInt(), color.alpha
        )
    }

    fun rainbow(speed: Int, index: Int, saturation: Float, brightness: Float, opacity: Float): Color {
        val angle = ((System.currentTimeMillis() / speed + index) % 360).toInt()
        val hue = angle / 360f
        val color = Color(Color.HSBtoRGB(hue, saturation, brightness))
        return Color(
            color.red, color.green, color.blue, max(
                0.0, min(
                    255.0,
                    (opacity * 255).toInt().toDouble()
                )
            ).toInt()
        )
    }

    fun interpolateColorsBackAndForth(speed: Int, index: Int, start: Color, end: Color, trueColor: Boolean): Color {
        var angle = (((System.currentTimeMillis()) / speed + index) % 360).toInt()
        angle = (if (angle >= 180) 360 - angle else angle) * 2
        return if (trueColor) interpolateColorHue(start, end, angle / 360f) else interpolateColorC(
            start,
            end,
            angle / 360f
        )
    }

    fun interpolateColorC(color1: Color, color2: Color, amount: Float): Color {
        var amount = amount
        amount = min(1.0, max(0.0, amount.toDouble())).toFloat()
        return Color(
            interpolateInt(color1.red, color2.red, amount.toDouble()),
            interpolateInt(color1.green, color2.green, amount.toDouble()),
            interpolateInt(color1.blue, color2.blue, amount.toDouble()),
            interpolateInt(color1.alpha, color2.alpha, amount.toDouble())
        )
    }

    fun interpolateColorHue(color1: Color, color2: Color, amount: Float): Color {
        var amount = amount
        amount = min(1.0, max(0.0, amount.toDouble())).toFloat()

        val color1HSB = Color.RGBtoHSB(color1.red, color1.green, color1.blue, null)
        val color2HSB = Color.RGBtoHSB(color2.red, color2.green, color2.blue, null)

        val resultColor = Color.getHSBColor(
            interpolateFloat(color1HSB[0], color2HSB[0], amount.toDouble()), interpolateFloat(
                color1HSB[1], color2HSB[1], amount.toDouble()
            ), interpolateFloat(color1HSB[2], color2HSB[2], amount.toDouble())
        )

        return Color(
            resultColor.red,
            resultColor.green,
            resultColor.blue,
            interpolateInt(color1.alpha, color2.alpha, amount.toDouble())
        )
    }

    fun interpolate(oldValue: Double, newValue: Double, interpolationValue: Double): Double {
        return (oldValue + (newValue - oldValue) * interpolationValue)
    }

    fun interpolateFloat(oldValue: Float, newValue: Float, interpolationValue: Double): Float {
        return interpolate(oldValue.toDouble(), newValue.toDouble(), interpolationValue.toFloat().toDouble()).toFloat()
    }

    fun interpolateInt(oldValue: Int, newValue: Int, interpolationValue: Double): Int {
        return interpolate(oldValue.toDouble(), newValue.toDouble(), interpolationValue.toFloat().toDouble()).toInt()
    }


    //http://www.java2s.com/example/java/2d-graphics/check-if-a-color-is-more-dark-than-light.html
    fun isDark(color: Color): Boolean {
        return isDark(color.red / 255.0f, color.green / 255.0f, color.blue / 255.0f)
    }

    fun isDark(r: Float, g: Float, b: Float): Boolean {
        return colorDistance(r, g, b, 0f, 0f, 0f) < colorDistance(r, g, b, 1f, 1f, 1f)
    }

    fun colorDistance(r1: Float, g1: Float, b1: Float, r2: Float, g2: Float, b2: Float): Float {
        val a = r2 - r1
        val b = g2 - g1
        val c = b2 - b1
        return sqrt((a * a + b * b + c * c).toDouble()).toFloat()
    }
}
