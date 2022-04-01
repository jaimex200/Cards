package es.uam.eps.dadm.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import es.uam.eps.dadm.cards.databinding.ListItemCardBinding

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardHolder>() {
    lateinit var binding: ListItemCardBinding
    var data = listOf<Card>()

    inner class CardHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var local = binding
        fun bind(card: Card) {
            local.card = card
            itemView.setOnClickListener {
                Toast.makeText(it.context, card.question, Toast.LENGTH_SHORT).show()
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
        holder.bind(data[position])
    }
}