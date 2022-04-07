package es.uam.eps.dadm.cards

import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import es.uam.eps.dadm.cards.databinding.ListItemCardBinding
import es.uam.eps.dadm.cards.src.Card
import es.uam.eps.dadm.cards.src.Cloze
import es.uam.eps.dadm.cards.src.Options

class CardAdapter (resources: Resources) : RecyclerView.Adapter<CardAdapter.CardHolder>() {
    lateinit var binding: ListItemCardBinding
    var data = listOf<Card>()
    lateinit var dataDeck: String
    var res = resources

    inner class CardHolder(view: View, resources: Resources) : RecyclerView.ViewHolder(view) {
        private var local = binding
        private var resources = resources
        fun bind(card: Card, deckid: String) {
            local.card = card
            if (card is Cloze) {
                local.type =  resources.getString(R.string.cloze_card_text)
                itemView.setOnClickListener {
                    it.findNavController()
                        .navigate(CardListFragmentDirections.actionCardListFragmentToClozeEditFragment(card.id, deckid))
                }
            }
            else if (card is Options) {
                local.type =  resources.getString(R.string.optional_card_text)
                itemView.setOnClickListener {
                    it.findNavController()
                        .navigate(CardListFragmentDirections.actionCardListFragmentToOptionalEditFragment(card.id, deckid))
                }
            }
            else {
                local.type =  resources.getString(R.string.normal_card_text)
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
        return CardHolder(binding.root, res)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(data[position], dataDeck)
    }
}