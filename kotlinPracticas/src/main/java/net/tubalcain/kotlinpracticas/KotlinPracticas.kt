package net.tubalcain.kotlinpracticas


fun main() {
    clearConsole()
    ejemplo03()
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
    val matriculado = true
    val repetidor = true

    if (peso > 80) {
        println("Peso supera la media")
    } else {
        println("Peso inferior a la media")
    }

    if (!matriculado) {
        println("Esta persona no es actualmente alumno.")
    }

    if(matriculado && repetidor) {
        println("Este alumno esta actualmente matriculado y es repetidor.")
    }
}

