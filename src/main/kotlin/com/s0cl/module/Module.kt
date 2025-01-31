package com.s0cl.module

import com.s0cl.Canvas
import com.s0cl.surveillance.PacketSurveillance
import com.s0cl.util.IMinecraft

import net.minecraft.network.packet.Packet

/**
 * @author yuxiangll
 * @since 2024/7/6 上午9:26
 * IntelliJ IDEA
 */


@Suppress("MemberVisibilityCanBePrivate")
abstract class Module(
    val name: String,
    val category: Category,
) : IMinecraft {

    var enable: Boolean = false
    var prefix: String = ""
    var bind: BindSetting = BindSetting(name,"Binding", Bind(0, BindType.PreClick))
    var unfold: Boolean = false
    val settings: MutableList<Settings> = mutableListOf()

    open fun onEnable(){

    }

    open fun onDisable(){

    }

    fun addSetting(setting: Settings) {
        settings.add(setting)
    }

    fun addSetting(setting: MutableList<Settings>){
        settings.addAll(setting)
    }

    fun toggle(){
        if(enable)
            disable()
        else
            enable()

    }
    fun toggle(enable: Boolean) {
        if(enable)
            enable()
        else
            disable()

    }

    private fun enable(){
        enable = true
        Canvas.EventBus.subscribe(this)
        onEnable()
    }

    private fun disable(){
        enable = false
        Canvas.EventBus.unsubscribe(this)
        onDisable()

    }


    protected fun sendPacketSilent(packet: Packet<*>) {
        if (mc.networkHandler == null) return
        PacketSurveillance.silentPackets.add(packet)
        mc.networkHandler!!.sendPacket(packet)
    }




}