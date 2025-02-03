package com.s0cl.module.imp.render

import com.s0cl.events.EventRender3D
import com.s0cl.module.Bind
import com.s0cl.module.Category
import com.s0cl.module.Module
import com.s0cl.util.player.PlayerUtils
import com.s0cl.util.render.gl.render.RenderUtils
import meteordevelopment.orbit.EventHandler
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.EntityType
import net.minecraft.item.AirBlockItem
import net.minecraft.item.EnderPearlItem
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import org.lwjgl.glfw.GLFW
import oshi.util.FormatUtil.roundToInt
import java.awt.Color
import kotlin.math.round
import kotlin.math.sqrt

/**
 * @author yuxiangll
 * @since 2025/2/1 下午5:23
 * IntelliJ IDEA
 */
object Projectiles : Module("Projectiles",Category.RENDER) {

    //todo only for end pearl
    init {
        this.bind.value = Bind(GLFW.GLFW_KEY_R)
    }





    @EventHandler
    fun onRender3D(event : EventRender3D){
        if(mc.player == null) return
        if (mc.world == null) return

        val player = mc.player as ClientPlayerEntity
        val world = mc.world as ClientWorld


        val item = player.mainHandStack.item
        if (item !is EnderPearlItem)return
        //val camera = player.
        val landingPos = PlayerUtils.calculateEnderPearlLandingPos(player)
        //println(landingPos)
        RenderUtils.draw3DBox(
            event.matrixStack,
            Box(BlockPos(roundToInt(landingPos.x),roundToInt(landingPos.y),roundToInt(landingPos.z))),Color(255,255,255,100),1f
        )
//        world.entities.filter { it.type == EntityType.ENDER_PEARL }.forEach{
//            println("x,y,z velocity ${it.velocity} total velocity: ${sqrt(
//                it.velocity.x * it.velocity.x + it.velocity.y * it.velocity.y + it.velocity.z * it.velocity.z)}")
//        }
        //println(item)


    }


    fun calculateEndPearl(){
        val initialSpeed = 52f










    }


}