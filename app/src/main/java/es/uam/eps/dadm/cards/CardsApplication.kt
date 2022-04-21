package es.uam.eps.dadm.cards

import android.app.Application
import es.uam.eps.dadm.cards.database.CardDatabase
import es.uam.eps.dadm.cards.src.Card
import es.uam.eps.dadm.cards.src.Deck
import timber.log.Timber
import java.util.concurrent.Executors

class CardsApplication: Application() {
    private val executor = Executors.newSingleThreadExecutor()

    init {
        /**ENCODE
        cards.add(Card("To wake up", "Despertarse"))
        cards.add(Card("To rule out", "Descartar"))
        cards.add(Card("To turn down", "Rechazar"))

        for (i in 1..100)
            cards.add(Card("Question number $i", "Answer number $i"))
        ***************************/
        decks.add(Deck("pruebaDeck"))
        decks[0].addOne(Card("To rule out", "Descartar"))
    }

    override fun onCreate() {
        super.onCreate()
        /**ENCODE
        val cardDatabase = CardDatabase.getInstance(applicationContext)
        executor.execute {
            cardDatabase.cardDao.addCard(
                Card("To wake up", "Despertarse")
            )
            cardDatabase.cardDao.addCard(
                Card("To rule out", "Descartar")
            )
            cardDatabase.cardDao.addCard(
                Card("To turn down", "Rechazar")
            )
        }
        ***************************/
        val cardDatabase = CardDatabase.getInstance(applicationContext)
        executor.execute {
            cardDatabase.deckDao.addDeck(
                Deck("pruebaDeck1db")
            )
            cardDatabase.deckDao.addDeck(
                Deck("pruebaDeck2db")
            )
            cardDatabase.deckDao.addDeck(
                Deck("pruebaDeck3db")
            )
        }

        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var decks: MutableList<Deck> = mutableListOf<Deck>()

        /**ENCODE
        var cards: MutableList<Card> = mutableListOf()
        fun numberOfDueCards() = cards.filter { it.isDue(LocalDateTime.now()) }.size
        fun getCard(id: String) = cards.find { it.id == id}
        fun addCard(card: Card) {
            cards.add(card)
        }
        ***************************/

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