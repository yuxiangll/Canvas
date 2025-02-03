package com.s0cl.util.player

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * @author yuxiangll
 * @since 2025/2/1 下午5:58
 * IntelliJ IDEA
 */
object PlayerUtils {


    fun calculateEnderPearlLandingPos(player: PlayerEntity): Vec3d {
        // 初速度
        val initialSpeed = 1.5f // 末影珍珠的初速度 (m/tick)
        val pitch = Math.toRadians(player.pitch.toDouble()) // 俯仰角（转换为弧度）
        val yaw = Math.toRadians(player.yaw.toDouble()) // 偏航角（转换为弧度）

        // 反转 pitch 和 yaw 的方向
        val correctedYaw = -yaw
        val correctedPitch = -pitch

        // 分解初速度
        val vx0 = initialSpeed * cos(correctedPitch) * sin(correctedYaw) // x 轴速度
        val vz0 = initialSpeed * cos(correctedPitch) * cos(correctedYaw) // z 轴速度
        val vy0 = initialSpeed * sin(correctedPitch) // y 轴速度

        // 重力加速度
        val g = 0.03 // Minecraft 中的重力加速度 (m/tick²)

        // 初始高度（玩家的眼睛高度）
        val h = player.eyePos.y-player.y

        // 计算飞行时间
        val t = (vy0 + sqrt(vy0 * vy0 + 2 * g * h)) / g

        // 计算水平位移
        val dx = vx0 * t
        val dz = vz0 * t

        // 计算落点坐标
        val xLanding = player.x + dx
        val zLanding = player.z + dz

        // 返回落点坐标
        return Vec3d(xLanding, player.y, zLanding)
    }


}