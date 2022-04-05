package es.uam.eps.dadm.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.uam.eps.dadm.cards.src.Card
import java.time.LocalDateTime

class StudyViewModel: ViewModel() {
    var card: Card? = null
    private var cards: MutableList<Card> = CardsApplication.cards
    val nDueCards: LiveData<Int>
        get() = _nDueCards
    private val _nDueCards = MutableLiveData<Int>()

    init {
        card = random_card()
        _nDueCards.value = dueCards().size
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
        _nDueCards.value = nDueCards.value?.minus(1)
    }
}