package com.example.imccalculator

import kotlin.math.pow

fun calcularImc(peso: String, altura: String): Double{

    var doublePeso: Double = peso.toDouble()
    var doubleAltura: Double = altura.toDouble()

    var result: Double = doublePeso/doubleAltura.pow(2)

    return result

}