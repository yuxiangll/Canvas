package com.s0cl.command

import com.mojang.brigadier.CommandDispatcher
import com.s0cl.managers.CommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.text.Text

/**
 * @author yuxiangll
 * @since 2024/7/7 下午7:17
 * IntelliJ IDEA
 */
object HelpCommand: Command("help","display help") {

    val showHelp = {
        CommandManager.commands.forEachIndexed{ index, command ->
            send("$index /canvas ${command.name}    ->   ${command.description}")
        }
    }

    override fun initializeCommand() {
        ClientCommandRegistrationCallback.EVENT.register(ClientCommandRegistrationCallback {
                dispatcher: CommandDispatcher<FabricClientCommandSource?>, _ ->

            dispatcher.register(literal("canvas")
                .executes {
                    it.source.sendFeedback(Text.literal("Using /canvas help"))
                    1
                }

                .then(
                    literal("help")
                        .executes{
                            showHelp()
                            1
                        }
                )



            )
        })
    }


}