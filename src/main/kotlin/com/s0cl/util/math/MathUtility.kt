package com.s0cl.util.math

import com.s0cl.util.IMinecraft
import net.minecraft.entity.Entity
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.acos
import kotlin.math.min
import kotlin.math.sqrt

object MathUtility : IMinecraft {
    fun random(min: Double, max: Double): Double {
        return ThreadLocalRandom.current().nextDouble() * (max - min) + min
    }

    fun random(min: Float, max: Float): Float {
        return (Math.random() * (max - min) + min).toFloat()
    }

    fun getDistanceSq(x: Double, y: Double, z: Double): Double {
        val d0 = mc.player!!.x - x
        val d1 = mc.player!!.y - y
        val d2 = mc.player!!.z - z
        return d0 * d0 + d1 * d1 + d2 * d2
    }

    fun getDistance(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double): Double {
        val d0 = (x1 - x2)
        val d1 = (y1 - y2)
        val d2 = (z1 - z2)
        return sqrt(d0 * d0 + d1 * d1 + d2 * d2)
    }

    fun getSqrDistance(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double): Double {
        val d0 = (x1 - x2)
        val d1 = (y1 - y2)
        val d2 = (z1 - z2)
        return sqrt(d0 * d0 + d1 * d1 + d2 * d2)
    }

    fun round(value: Float): Float {
        var bd = BigDecimal(value.toDouble())
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toFloat()
    }

    fun getDistanceSq(ent: Entity): Double {
        return getDistanceSq(ent.x, ent.y, ent.z)
    }

    fun angle(vec3d: Vec3d, other: Vec3d): Double {
        val lengthSq = vec3d.length() * other.length()

        if (lengthSq < 1.0E-4) {
            return 0.0
        }

        val dot = vec3d.dotProduct(other)
        val arg = dot / lengthSq

        if (arg > 1) {
            return 0.0
        } else if (arg < -1) {
            return 180.0
        }

        return acos(arg) * 180.0f / Math.PI
    }

    fun fromTo(from: Vec3d, x: Double, y: Double, z: Double): Vec3d {
        return fromTo(from.x, from.y, from.z, x, y, z)
    }

    fun lerp(f: Float, st: Float, en: Float): Float {
        return st + f * (en - st)
    }

    fun fromTo(x: Double, y: Double, z: Double, x2: Double, y2: Double, z2: Double): Vec3d {
        return Vec3d(x2 - x, y2 - y, z2 - z)
    }

    fun rad(angle: Float): Float {
        return (angle * Math.PI / 180).toFloat()
    }

    fun clamp(num: Int, min: Int, max: Int): Int {
        return if (num < min) min else min(num.toDouble(), max.toDouble()).toInt()
    }

    fun clamp(num: Float, min: Float, max: Float): Float {
        return if (num < min) min else min(num.toDouble(), max.toDouble()).toFloat()
    }

    fun clamp(num: Double, min: Double, max: Double): Double {
        return if (num < min) min else min(num, max)
    }

    fun sin(value: Float): Float {
        return MathHelper.sin(value)
    }

    fun cos(value: Float): Float {
        return MathHelper.cos(value)
    }

    fun wrapDegrees(value: Float): Float {
        return MathHelper.wrapDegrees(value)
    }

    fun wrapDegrees(value: Double): Double {
        return MathHelper.wrapDegrees(value)
    }

    fun square(input: Double): Double {
        return input * input
    }

    fun round(value: Double, places: Int): Double {
        var bd = BigDecimal.valueOf(value)
        bd = bd.setScale(places, RoundingMode.HALF_UP)
        return bd.toDouble()
    }

    fun wrap(valI: Float): Float {
        var `val` = valI % 360.0f
        if (`val` >= 180.0f) {
            `val` -= 360.0f
        }
        if (`val` < -180.0f) {
            `val` += 360.0f
        }
        return `val`
    }

    fun direction(yaw: Float): Vec3d {
        return Vec3d(
            kotlin.math.cos(degToRad((yaw + 90.0f).toDouble())),
            0.0,
            kotlin.math.sin(degToRad((yaw + 90.0f).toDouble()))
        )
    }

    fun round(value: Float, places: Int): Float {
        require(places >= 0)
        var bd = BigDecimal.valueOf(value.toDouble())
        bd = bd.setScale(places, RoundingMode.FLOOR)
        return bd.toFloat()
    }

    fun round2(value: Double): Float {
        var bd = BigDecimal(value)
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toFloat()
    }


    fun degToRad(deg: Double): Double {
        return deg * 0.01745329238474369
    }
}
