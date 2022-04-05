package es.uam.eps.dadm.cards.src

object Stadistics {
    var success: Int = 0
    var doubt: Int = 0
    var error: Int = 0
    var listCards: MutableList<Card> = mutableListOf()
    fun show () {

        if (success != 0) println("Porcentaje de aciertos: ${(success.toDouble()/(success + error + doubt))*100}%") else println("Porcentaje de aciertos: 0%" )

        println("Tarjetas mas dificiles")
        for (c in listCards) {
            c.showCard()
        }
    }

    fun addSuccess () {
        success += 1
    }

    fun adddoubt () {
        doubt += 1
    }

    fun addError() {
        error += 1
    }

    fun addCard(c: Card) {
        if (listCards.contains(c)) return else listCards.add(c)
    }

    fun deleteCard(c: Card) {
        if (listCards.contains(c)) listCards.remove(c)
    }
}