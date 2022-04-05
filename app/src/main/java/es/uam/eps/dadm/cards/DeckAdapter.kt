package es.uam.eps.dadm.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import es.uam.eps.dadm.cards.databinding.ListItemDeckBinding
import es.uam.eps.dadm.cards.src.Deck

class DeckAdapter: RecyclerView.Adapter<DeckAdapter.DeckHolder>() {
    lateinit var binding: ListItemDeckBinding
    var data = listOf<Deck>()

    inner class DeckHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var local = binding
        fun bind(deck: Deck) {
            local.deck = deck
            local.editDeckFab.setOnClickListener {
                it.findNavController()
                    .navigate(DeckListFragmentDirections.actionDeckListFragmentToDeckEditFragment(deck.id))
            }
            itemView.setOnClickListener {
                if (deck.cards.size > 0)
                    it.findNavController()
                        .navigate(DeckListFragmentDirections.actionDeckListFragmentToCardListFragment(deck.id))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckAdapter.DeckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ListItemDeckBinding.inflate(layoutInflater, parent, false)
        return DeckHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DeckHolder, position: Int) {
        holder.bind(data[position])
    }
}