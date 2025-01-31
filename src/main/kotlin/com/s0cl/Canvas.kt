package com.s0cl

import com.s0cl.managers.CommandManager
import com.s0cl.managers.ModuleManager
import com.s0cl.managers.SurveillanceManager
import meteordevelopment.orbit.EventBus
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles
import java.lang.reflect.Method

object Canvas : ModInitializer {
	private val logger = LoggerFactory.getLogger("Canvas")
	const val NAME = "Canvas"
	const val VERSION = "1.0.0"

	var FirstTimeLoad = false

	val EventBus = EventBus()


	override fun onInitialize() {


		EventBus.registerLambdaFactory( "com.s0cl") { lookupInMethod: Method, klass: Class<*> ->
			lookupInMethod(null, klass, MethodHandles.lookup()) as MethodHandles.Lookup
		}


		logger.info("initializing managers")

		ModuleManager.initialize()
		SurveillanceManager.initialize()
		CommandManager.initialize()

	}
}