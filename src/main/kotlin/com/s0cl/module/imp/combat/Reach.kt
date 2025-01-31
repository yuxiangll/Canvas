package com.s0cl.module.imp.combat

import com.s0cl.module.Category
import com.s0cl.module.Module
import com.s0cl.module.NumberSetting

/**
 * @author yuxiangll
 * @since 2024/7/14 下午4:35
 * IntelliJ IDEA
 */

object Reach : Module("Reach", Category.COMBAT) {
    val blockRange = NumberSetting("BlockRange","Modify your range",1.0..100.0,50.0)
    val attackRange = NumberSetting("AttackRange","Modify your range",1.0..100.0,50.0)


    init {
        this.addSetting(blockRange)
        this.addSetting(attackRange)
    }
}