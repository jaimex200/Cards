package es.uam.eps.dadm.cards

import android.app.Application
import es.uam.eps.dadm.cards.src.Card
import es.uam.eps.dadm.cards.src.Deck
import timber.log.Timber

class CardsApplication: Application() {

    init {
        decks.add(Deck("pruebaDeck"))
        decks[0].addOne(Card("To rule out", "Descartar"))
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var decks: MutableList<Deck> = mutableListOf<Deck>()

        fun numberOfDecks (): Int {
            return decks.size
        }

        fun getCard(cardid: String, deckid: String): Card? {
            var cards = getDeck(deckid)!!.cards
            for (card in cards) {
                if (card.id == cardid) {
                    return card
                }
            }
            return null
        }

        fun getDeck(deckid: String): Deck? {
            for (deck in decks) {
                if (deck.id == deckid) {
                    return deck
                }
            }
            return null
        }
    }
}