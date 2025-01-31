package com.s0cl.surveillance

import com.s0cl.Canvas
import com.s0cl.events.EventDeath
import com.s0cl.events.EventPrePacketReceive
import com.s0cl.events.EventPrePlayerUpdate
import com.s0cl.events.EventTotemPop
import com.s0cl.util.IMinecraft
import meteordevelopment.orbit.EventHandler
import net.minecraft.entity.EntityStatuses
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket

/**
 * @author yuxiangll
 * @since 2024/7/8 下午3:07
 * IntelliJ IDEA
 */
object EventSurveillance : IMinecraft {


    @EventHandler
    fun tick(event: EventPrePlayerUpdate){

        mc.world?.getPlayers()?.forEach {
            if (it.isDead || it.health == 0F) Canvas.EventBus.post(EventDeath(it))
        }


    }


    @EventHandler
    fun onPacketReceive(event: EventPrePacketReceive){

        when (event.packet){
            is EntityStatusS2CPacket -> {
                if (event.packet.status == EntityStatuses.USE_TOTEM_OF_UNDYING){
                    val event = EventTotemPop(event.packet.getEntity(mc.world) as PlayerEntity)
                    Canvas.EventBus.post(event)
                }


            }
        }



    }






}