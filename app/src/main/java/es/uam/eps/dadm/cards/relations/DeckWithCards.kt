package es.uam.eps.dadm.cards.relations

import androidx.room.Embedded
import androidx.room.Relation
import es.uam.eps.dadm.cards.src.Card
import es.uam.eps.dadm.cards.src.Deck

data class DeckWithCards(
    @Embedded
    val deck: Deck,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )

    val cards: List<Card>
)