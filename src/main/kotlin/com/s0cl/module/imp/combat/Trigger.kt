package com.s0cl.module.imp.combat

import com.s0cl.events.EventPostPlayerUpdate
import com.s0cl.module.Category
import com.s0cl.module.Module
import meteordevelopment.orbit.EventHandler
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult

/**
 * @author yuxiangll
 * @since 2024/7/14 下午5:14
 * IntelliJ IDEA
 */
object Trigger : Module("Trigger", Category.COMBAT) {



    @EventHandler
    fun onAttack(event: EventPostPlayerUpdate){


        if ( mc.crosshairTarget == null) return
        if (mc.player?.getAttackCooldownProgress(0.5f)!! < 1f) return
        val ent = mc.crosshairTarget
        when (ent){
            is EntityHitResult -> {
                mc.interactionManager?.attackEntity(mc.player,ent.entity)
                mc.player?.swingHand(Hand.MAIN_HAND)
            }
        }



    }


}