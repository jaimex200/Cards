package es.uam.eps.dadm.cards.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.uam.eps.dadm.cards.src.Deck

@Dao
interface DeckDao {
    @Query("SELECT * FROM decks_table")
    fun getDecks(): LiveData<List<Deck>>

    @Query("SELECT * FROM decks_table WHERE id = :id")
    fun getDeck(id: String): LiveData<Deck?>

    @Insert
    fun addDeck(deck: Deck)

    @Update
    fun update(deck: Deck)
}