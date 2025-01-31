package com.s0cl.managers

import net.minecraft.resource.ResourceReloader
import net.minecraft.text.Text

/**
 * @author yuxiangll
 * @since 2024/8/10 上午10:25
 * IntelliJ IDEA
 */
object LanguageStateManager {
    val reloaders = ArrayList<ResourceReloader>()
    var resourceLoadViaLanguage = false;


    fun addResourceReloader(reloader: ResourceReloader) {
        reloaders.add(reloader)
    }

    fun isMatchable(input: String, definition: Text): Boolean {
        return isMatchable(input, definition.string)
    }

    fun isMatchable(input: String, definition: String): Boolean {
        return definition.lowercase().contains(input.lowercase())
    }




}