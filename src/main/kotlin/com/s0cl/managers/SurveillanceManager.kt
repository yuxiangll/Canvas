package com.s0cl.managers


import com.s0cl.surveillance.EventSurveillance
import com.s0cl.surveillance.ModuleSurveillance
import com.s0cl.Canvas


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

        surveillance.forEach {
            Canvas.EventBus.subscribe(it)
        }

    }

}