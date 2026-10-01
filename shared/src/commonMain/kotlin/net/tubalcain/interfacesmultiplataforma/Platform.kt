package net.tubalcain.interfacesmultiplataforma

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform