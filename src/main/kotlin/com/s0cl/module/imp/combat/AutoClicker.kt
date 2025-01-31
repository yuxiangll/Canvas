package com.s0cl.module.imp.combat

import com.s0cl.events.EventPrePlayerUpdate
import com.s0cl.module.Category
import com.s0cl.module.Module

import meteordevelopment.orbit.EventHandler
import net.minecraft.util.Hand

/**
 * @author yuxiangll
 * @since 2024/7/7 下午9:44
 * IntelliJ IDEA
 */
object AutoClicker: Module("AutoClicker", Category.COMBAT) {

//    override fun onEnable() {
//        mc.player?.sendMessage(Text.of("开启"))
//    }
//
//    override fun onDisable() {
//        mc.player?.sendMessage(Text.of("关闭"))
//    }
//

    @EventHandler
    fun onUpdate(event: EventPrePlayerUpdate){
        if (mc.player == null) return
        mc.player!!.swingHand(Hand.OFF_HAND)

    }





//    @EventHandler
//    fun onRender(event: EventRenderGameOverlay){
//        FontManager.pingFang16.drawString(event.context.matrices,"中文测试",100.0,100.0,-1)
//        FontManager.pingFang16.drawGradientString(event.context.matrices,"中文测试", 100F,200F, Color(130,160,233), Color(233,123,123),50)
//    }


}