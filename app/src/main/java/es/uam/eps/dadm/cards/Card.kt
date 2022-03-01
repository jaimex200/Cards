package es.uam.eps.dadm.cards

import android.view.View
import java.time.*
import java.util.UUID
import kotlin.math.roundToLong

open class Card(
    var question: String,
    var answer: String,
    var quality: Int = 0,
    var repetitions: Int = 0,
    var interval: Long = 1L,
    var nextPracticeDate: String = LocalDateTime.now().toString(),
    var easiness: Double = 2.5,
    var date: String = LocalDateTime.now().toString(),
    var id: String = UUID.randomUUID().toString(),
    var answered: Boolean = false
) {
    companion object Load{
        fun fromString(cad: String): Card {
            val data = cad.replace(" ","").split("|")
            val card = Card(data[1], data[2])

            card.quality = data[3].toInt()
            card.repetitions = data[4].toInt()
            card.interval = data[5].toLong()
            card.nextPracticeDate = data[6]
            card.easiness = data[7].toDouble()
            card.date = data[8]
            card.id = data[9]

            return card
        }
    }

    /**Funcion para hacer una tarjeta*/
    open fun show () {
        print(" $question (INTRO para ver respuesta) ")
        readLine()
        print(" $answer (Teclea 0, 3 o 5): ")
        val userAns = readLine()?.toIntOrNull() ?: -1
        quality = userAns
    }

    open fun defaultToString() =
        "$question | $answer | $quality | $repetitions | $interval | $nextPracticeDate | $easiness | $date | $id \n"

    override fun toString() = "card | " + defaultToString()

    /**Funcion para enseñar la tarjeta*/
    open fun showCard () {
        println("$question -> $answer")
    }

    /**Funcion para ejecutar la tarjeta*/
    fun execCard (currentDate: LocalDateTime) {
        if (currentDate.toString().split('T')[0] == nextPracticeDate.split('T')[0]) {
            show()
            update(currentDate)
            details()
        }
    }

    /**Funcion para actualizar la tarjeta*/
    fun update (currentDate: LocalDateTime) {
        easinessAlg()
        repetitionAlg()
        intervalAlg()
        updateStats()

        nextPracticeDate = currentDate.plusDays(interval).toString()
    }

    /**Funcion para actualizar desde view*/
    fun update_from_view(view: View) {
        quality = when(view.id) {
            R.id.easy_button -> 5
            R.id.doubt_button -> 3
            R.id.hard_button -> 0
            else -> throw Exception("Unavailable quality")
        }
        update(LocalDateTime.now())
    }

    /**Funcion para actualizar las estadisticas*/
    private fun updateStats() {
        when(quality) {
            0 -> Stadistics.addError()
            3 -> Stadistics.adddoubt()
            else -> Stadistics.addSuccess()
        }

        if (easiness == 1.3) Stadistics.addCard(this) else Stadistics.deleteCard(this)
    }

    /**Funcion para enseñar los detalles de la tarjeta*/
    private fun details () {
        println(" eas = ${"%.2f".format(easiness)} rep = $repetitions int = $interval next = ${nextPracticeDate.split('T')[0]}")
    }

    /**Simulacion de la tarjeta*/
    fun simulate(period: Long) {
        println("Simulación de la tarjeta $question:")
        var now = LocalDateTime.now()

        for (p in 1..period) {
            println("Fecha: $now")
            if (now.toString().split('T')[0] == nextPracticeDate.split('T')[0]) {
                show()
                update(now)
            }
            now = now.plusDays(1)
        }
    }

    /**Funcion auxiliar*/
    private fun easinessAlg () {
        val easinessAlg = easiness + 0.1 - (5.0 - quality) * (0.08 + (5.0 - quality) * 0.02)

        easiness = maxOf(easinessAlg, 1.3)

    }

    /**Funcion auxiliar*/
    private fun repetitionAlg () {
        if (quality < 3) repetitions = 0 else repetitions += 1
    }

    /**Funcion auxiliar*/
    private fun intervalAlg () {
        interval = if (repetitions <= 1) 1 else if (repetitions == 2) 6 else (interval.toDouble() * easiness).roundToLong()
    }
}