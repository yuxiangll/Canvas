package com.s0cl.module.imp.world

import com.s0cl.events.EventPrePlayerUpdate
import com.s0cl.inject.accessor.net.minecraft.client.IMinecraftClient
import com.s0cl.module.Category
import com.s0cl.module.Module
import meteordevelopment.orbit.EventHandler

/**
 * @author yuxiangll
 * @since 2024/7/14 下午5:53
 * IntelliJ IDEA
 */
object FastPlace : Module("FastPlace", Category.WORLD) {


    @EventHandler
    fun onPlace(event: EventPrePlayerUpdate) {
        (mc as IMinecraftClient).useCooldown = 0
    }


}