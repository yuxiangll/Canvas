package com.s0cl.managers


import com.s0cl.surveillance.EventSurveillance
import com.s0cl.surveillance.ModuleSurveillance
import com.s0cl.Canvas
import com.s0cl.surveillance.RenderSurveillance


/**
 * @author yuxiangll
 * @since 2024/7/6 上午9:53
 * IntelliJ IDEA
 */

object SurveillanceManager {
    private val surveillance = mutableListOf<Any>()

    fun initialize(){
        surveillance.add(ModuleSurveillance)
        surveillance.add(EventSurveillance)
        surveillance.add(RenderSurveillance)
        surveillance.forEach {
            Canvas.EventBus.subscribe(it)
        }

    }

}