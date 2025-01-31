package com.s0cl.managers

import com.s0cl.command.*


/**
 * @author yuxiangll
 * @since 2024/7/7 下午7:35
 * IntelliJ IDEA
 */
object CommandManager {
    val commands = mutableListOf<Command>()


    fun initialize(){
        commands.add(HelpCommand)
        commands.add(ToggleCommand)
        commands.add(BindCommand)
        commands.add(SetCommand)
        commands.forEach { it.initializeCommand() }
    }




}