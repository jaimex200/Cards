package es.uam.eps.dadm.cards

import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class MainViewModel: ViewModel() {
    var card: Card? = null
    private var cards: MutableList<Card> = mutableListOf<Card>()

    init {
        cards.add(Card("To wake up", "Despertarse"))
        cards.add(Card("To rule out", "Descartar"))
        card = random_card()
    }

    private fun dueCards() = cards.filter { card -> card.isDue(LocalDateTime.now()) }

    private fun random_card() =  try {
        dueCards().random()
    } catch (e: NoSuchElementException) {
        null
    }

    fun update(quality: Int) {
        card?.quality =  quality
        card?.update(LocalDateTime.now())
        card = random_card()
    }
}