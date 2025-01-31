package com.s0cl.util.animation

import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.function.Function
import kotlin.math.*

/**
 * This is the easing class which is used to manage the different easing functions.
 * Each function is supposed to return a value between 0 and 1.
 * More easing functions can be found here: [...](https://easings.net/)
 *
 * @author Patrick
 */
enum class Easing(val function: Function<Double, Double>) {
    LINEAR(Function<Double, Double> { x: Double -> x }),
    EASE_IN_QUAD(Function<Double, Double> { x: Double -> x * x }),
    EASE_OUT_QUAD(Function<Double, Double> { x: Double -> x * (2 - x) }),
    EASE_IN_OUT_QUAD(Function<Double, Double> { x: Double -> if (x < 0.5) 2 * x * x else -1 + (4 - 2 * x) * x }),
    EASE_IN_CUBIC(Function<Double, Double> { x: Double -> x * x * x }),
    EASE_OUT_CUBIC(Function<Double, Double> { x: Double ->
        var x = x
        (--x) * x * x + 1
    }),
    EASE_IN_OUT_CUBIC(Function<Double, Double> { x: Double -> if (x < 0.5) 4 * x * x * x else (x - 1) * (2 * x - 2) * (2 * x - 2) + 1 }),
    EASE_IN_QUART(Function<Double, Double> { x: Double -> x * x * x * x }),
    EASE_OUT_QUART(Function<Double, Double> { x: Double ->
        var x = x
        1 - (--x) * x * x * x
    }),
    EASE_IN_OUT_QUART(Function<Double, Double> { x: Double ->
        var x = x
        if (x < 0.5) 8 * x * x * x * x else 1 - 8 * (--x) * x * x * x
    }),
    EASE_IN_QUINT(Function<Double, Double> { x: Double -> x * x * x * x * x }),
    EASE_OUT_QUINT(Function<Double, Double> { x: Double ->
        var x = x
        1 + (--x) * x * x * x * x
    }),
    EASE_IN_OUT_QUINT(Function<Double, Double> { x: Double ->
        var x = x
        if (x < 0.5) 16 * x * x * x * x * x else 1 + 16 * (--x) * x * x * x * x
    }),
    EASE_IN_SINE(Function<Double, Double> { x: Double -> 1 - cos(x * Math.PI / 2) }),
    EASE_OUT_SINE(Function<Double, Double> { x: Double -> sin(x * Math.PI / 2) }),
    EASE_IN_OUT_SINE(Function<Double, Double> { x: Double -> 1 - cos(Math.PI * x / 2) }),
    EASE_IN_EXPO(Function<Double, Double> { x: Double -> (if (x == 0.0) 0 else 2.0.pow(10 * x - 10)).toDouble() }),
    EASE_OUT_EXPO(Function<Double, Double> { x: Double -> if (x == 1.0) 1.0 else 1 - 2.0.pow(-10 * x) }),
    EASE_IN_OUT_EXPO(Function<Double, Double> { x: Double ->
        if (x == 0.0) 0.0 else if (x == 1.0) 1.0 else if (x < 0.5) 2.0.pow(
            20 * x - 10
        ) / 2 else (2 - 2.0.pow(-20 * x + 10)) / 2
    }),
    EASE_IN_CIRC(Function<Double, Double> { x: Double -> 1 - sqrt(1 - x * x) }),
    EASE_OUT_CIRC(Function<Double, Double> { x: Double ->
        var x = x
        sqrt(1 - (--x) * x)
    }),
    EASE_IN_OUT_CIRC(Function<Double, Double> { x: Double -> if (x < 0.5) (1 - sqrt(1 - 4 * x * x)) / 2 else (sqrt(1 - 4 * (x - 1) * x) + 1) / 2 }),
    SIGMOID(Function<Double, Double> { x: Double? -> 1 / (1 + exp(-x!!)) }),
    EASE_OUT_ELASTIC(Function<Double, Double> { x: Double ->
        if (x == 0.0) 0.0 else if (x == 1.0) 1.0 else 2.0.pow(-10 * x) * sin(
            (x * 10 - 0.75) * ((2 * Math.PI) / 3)
        ) * 0.5 + 1
    }),
    EASE_IN_BACK(Function<Double, Double> { x: Double -> (1.70158 + 1) * x * x * x - 1.70158 * x * x }),
    EASE_IN_OUT_ELASTIC(Function<Double, Double> { x: Double ->
        val c5 = (2 * Math.PI) / 4.5
        val c6 = sin((20 * x - 11.125) * c5)
        if (x == 0.0
        ) 0.0
        else if (x == 1.0
        ) 1.0
        else if (x < 0.5
        ) -(2.0.pow(20 * x - 10) * c6) / 2
        else (2.0.pow(-20 * x + 10) * c6) / 2 + 1
    }),
    EASE_OUT_BOUNCE(Function<Double, Double> { x: Double ->
        var x = x
        val n1 = 7.5625
        val d1 = 2.75
        if (x < 1 / d1) {
            return@Function n1 * x * x
        } else if (x < 2 / d1) {
            return@Function n1 * ((1.5 / d1).let { x -= it; x }) * x + 0.75
        } else if (x < 2.5 / d1) {
            return@Function n1 * ((2.25 / d1).let { x -= it; x }) * x + 0.9375
        } else {
            return@Function n1 * ((2.625 / d1).let { x -= it; x }) * x + 0.984375
        }
    }),

    EASE_IN_OUT_BOUNCE(Function<Double, Double> { x: Double ->
        if (x < 0.5
        ) (1 - com.s0cl.util.animation.Easing.EASE_OUT_BOUNCE.function.apply(1 - 2 * x)) / 2
        else (1 + com.s0cl.util.animation.Easing.EASE_OUT_BOUNCE.function.apply(2 * x - 1)) / 2
    }),
    EASE_IN_OUT_BACK(Function<Double, Double> { x: Double ->
        val c1 = 1.70158
        val c2 = c1 * 1.525
        if (x < 0.5
        ) ((2 * x).pow(2.0) * ((c2 + 1) * 2 * x - c2)) / 2
        else ((2 * x - 2).pow(2.0) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2
    });

    override fun toString(): String {
        return StringUtils.capitalize(super.toString().lowercase(Locale.getDefault()).replace("_", " "))
    }
}