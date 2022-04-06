package es.uam.eps.dadm.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import es.uam.eps.dadm.cards.databinding.ListItemCardBinding
import es.uam.eps.dadm.cards.src.Card
import es.uam.eps.dadm.cards.src.Cloze

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardHolder>() {
    lateinit var binding: ListItemCardBinding
    var data = listOf<Card>()
    lateinit var dataDeck: String

    inner class CardHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var local = binding
        fun bind(card: Card, deckid: String) {
            local.card = card
            if (card is Cloze) {
                itemView.setOnClickListener {
                    it.findNavController()
                        .navigate(CardListFragmentDirections.actionCardListFragmentToClozeEditFragment(card.id, deckid))
                }
            }
            else {
                itemView.setOnClickListener {
                    it.findNavController()
                        .navigate(CardListFragmentDirections.actionCardListFragmentToCardEditFragment(card.id, deckid))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ListItemCardBinding.inflate(layoutInflater, parent, false)
        return CardHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(data[position], dataDeck)
    }
}