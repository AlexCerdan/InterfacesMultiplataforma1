package net.tubalcain.kotlinpracticas


fun main() {
    clearConsole()
    ejemplo01()
}


fun ejemplo01() {
    val nombre = "Juan"
    var edad = 23

    println("El nombre del cliente es: $nombre")
    println("La edad del cliente es: $edad")

    edad = edad + 2
    println("La nueva edad del cliente es: $edad")
}

fun ejemplo02() {
    val nombre: String = "Juan"
    val apellidos: String = "Gonzalez Diaz"
    var peso: Int = 83
    val estatura: Double = 1.75
    var domicilio: String = "Avda. Libertad, 8"
    val letra: Char = 'J'
}

