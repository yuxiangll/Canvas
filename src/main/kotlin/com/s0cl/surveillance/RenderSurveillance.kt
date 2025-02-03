package com.s0cl.surveillance

import com.s0cl.events.EventRenderSkija
import com.s0cl.util.render.skija.RenderProc
import meteordevelopment.orbit.EventHandler


/**
 * @author yuxiangll
 * @since 2025/2/1 下午2:48
 * IntelliJ IDEA
 */
object RenderSurveillance {

    @EventHandler
    fun onRenderSkija(event: EventRenderSkija){
        RenderProc.processRender()
    }

}