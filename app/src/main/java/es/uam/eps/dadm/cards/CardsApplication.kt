package es.uam.eps.dadm.cards

import android.app.Application
import timber.log.Timber

class CardsApplication: Application() {

    init {
        cards.add(Card("To wake up", "Despertarse"))
        cards.add(Card("To rule out", "Descartar"))
        for (i in 1..100){
            cards.add(Card("card question $i", "card answer $i"))
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var cards: MutableList<Card> = mutableListOf<Card>()

        fun numberOfDueCards (): Int {
            return cards.size
        }

        fun getCard(cardid: String): Card? {
            for (card in cards) {
                if (card.id == cardid) {
                    return card
                }
            }
            return null
        }
    }
}