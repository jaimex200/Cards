package es.uam.eps.dadm.cards.src

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "decks_table")
class Deck (
    @ColumnInfo(name = "deck_name")
    var name: String,
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var cards:  MutableList<Card> = mutableListOf(),
    var cardsErrors:  MutableList<Card> = mutableListOf(),

) {
    /** variable para el uso del nuevo alg*/
    var improveAlg = true

    /** añade carta al mazo*/
    fun addCard () {

        println("Añadiendo tarjeta al mazo ${name}:")
        print(" Teclea el tipo (0 -> Card 1 -> Cloze 2 -> Options): ")
        val typeCard = readLine()?.toIntOrNull() ?: return println(" Error al añadir tarjeta")

        print(" Teclea la pregunta: ")
        val question = readLine() ?: return println(" Error al añadir tarjeta")
        if (typeCard == 2) print(" Teclea todas las respuestas separadas por ',' (La primera sera la correcta): ") else print(" Teclea la respuesta: ")
        val answer = readLine() ?: return println(" Error al añadir tarjeta")

        when (typeCard) {
            0 -> cards.add(Card(question, answer))
            1 -> cards.add(Cloze(question, answer))
            else -> cards.add(Options(question, answer))
        }


        println(" Tarjeta añadida correctamente")
    }

    fun deleteCard () {
        var num = 1

        for (card in cards) {
            print(" $num. ")
            card.showCard()
            num += 1
        }

        print("Seleccione la tarjeta a eliminar o pulse ENTER: ")
        var choice = readLine()?.toIntOrNull() ?: return
        try {
            cards.removeAt(choice - 1)
        } catch (e: Exception) {
            println("Error al eliminar tarjeta")
            return
        }
        println("Tarjeta borrada correctamente")
    }

    /**Añade una tarjeta*/
    fun addOne(card: Card) {
        cards.add(card)
    }

    /**Enseña la lista de cartas del mazo*/
    fun listCards () {
        for (card in cards) {
            card.showCard()
        }
    }

    /**Repetir errores*/
    private fun execErrors () {
        while (cardsErrors.isNotEmpty()) {
            cardsErrors[0].show()
            if (cardsErrors[0].quality == 0) cardsErrors.add(cardsErrors[0])
            cardsErrors.removeAt(0)
        }
    }

    /**Simula el mazo*/
    fun simulate(period: Int) {
        var now = LocalDateTime.now()
        println("Simulación del mazo $name (0 -> difícil, 3 -> duda, 5 -> fácil):")

        // Bucle que recorre los días y las tarjetas
        for (p in 1..period) {
            println("Fecha actual: ${now.toString().split('T')[0]}")
            for (card in cards) {
                card.execCard(now)
                // Ampliacion del algoritmo
                if (card.quality == 0 && improveAlg) cardsErrors.add(card)
            }
            execErrors()
            now = now.plusDays(1)
        }
    }

    /**Escribe en fichero las tarjetas de un deck*/
    fun writeCards (name: String) {
        var cardsSave = ""
        for (card in cards) {
            cardsSave += card.toString()
        }
        File(name).writeText(cardsSave)
    }

    /**Lee de un fichero las rajetas*/
    fun readCards (name: String) {
        val lines: List<String> = File(name).readLines()

        for (line in lines) {
            if (line.contains("card")) addOne(Card.fromString(line)) else if (line.contains("cloze")) addOne(
                Cloze.fromString(line)
            ) else
                addOne(Options.fromString(line))
        }
    }
}