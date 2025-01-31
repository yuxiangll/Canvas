package com.s0cl.command

import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.s0cl.managers.ModuleManager
import com.s0cl.module.Bind
import com.s0cl.module.BindType
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback

/**
 * @author yuxiangll
 * @since 2024/8/10 下午6:29
 * IntelliJ IDEA
 */
object BindCommand : Command("bind","set module's keybinding (bindType: pre, post, hold)") {



    override fun initializeCommand() {
        ModuleManager.modules.forEach { module-> ClientCommandRegistrationCallback.EVENT.register(ClientCommandRegistrationCallback { dispatcher, _ ->
                dispatcher.register(
                    literal("canvas")
                        .then(literal("bind")
                            .then(literal(module.name)
                                .then(argument("KeyCode", IntegerArgumentType.integer())
                                    .executes{
                                        val key = IntegerArgumentType.getInteger(it, "KeyCode")
                                        module.bind.value = Bind(key)
                                        1
                                    }
                                    .then(argument("BindType", StringArgumentType.string())
                                        .executes {
                                            val type = StringArgumentType.getString(it, "BindType")
                                            val key = IntegerArgumentType.getInteger(it, "KeyCode")
                                            val castType = when(type.lowercase()){
                                                "pre" -> BindType.PreClick
                                                "post" -> BindType.PostClick
                                                "hold" -> BindType.Hold
                                                else -> BindType.PreClick
                                            }
                                            module.bind.value = Bind(key,castType)
                                            1
                                        }
                                    )

                                )

                            )

                        )
                )
            })
        }
    }
}