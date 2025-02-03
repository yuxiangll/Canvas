package com.s0cl.managers

import com.s0cl.module.Module
import com.s0cl.module.imp.combat.*
import com.s0cl.module.imp.player.*
import com.s0cl.module.imp.render.*
import com.s0cl.module.imp.world.*
import com.s0cl.Canvas
import com.s0cl.module.imp.combat.AutoClicker


/**
 * @author yuxiangll
 * @since 2024/7/6 上午9:42
 * IntelliJ IDEA
 */

@Suppress("MemberVisibilityCanBePrivate")
object ModuleManager {


    val modules = mutableListOf<Module>()



    fun initialize(){
        modules.add(AutoClicker)
        modules.add(ClickGui)
        modules.add(Reach)
        modules.add(Trigger)
        modules.add(FastPlace)
        modules.add(NoBreakCooldown)
        modules.add(BlockRenderTest)
        modules.add(Projectiles)
        Canvas.EventBus.subscribe(this)
    }






}