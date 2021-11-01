package com.codingCole

import com.codingCole.plugins.configureMonitoring
import com.codingCole.plugins.configureRouting
import com.codingCole.plugins.configureSerialization
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
    configureMonitoring()
}
