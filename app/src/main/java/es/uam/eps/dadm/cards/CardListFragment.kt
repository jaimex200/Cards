package es.uam.eps.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import es.uam.eps.dadm.cards.databinding.FragmentCardListBinding
import es.uam.eps.dadm.cards.src.Card

class CardListFragment: Fragment() {
    private lateinit var adapter: CardAdapter
    private lateinit var deckid: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCardListBinding>(
            inflater,
            R.layout.fragment_card_list,
            container,
            false
        )
        adapter = CardAdapter()
        deckid = CardListFragmentArgs.fromBundle(requireArguments()).deckid
        adapter.data = CardsApplication.getDeck(deckid)?.cards ?: throw Exception("Wrong id")
        adapter.dataDeck = deckid
        binding.cardListRecyclerView.adapter = adapter

        binding.newCardFab.setOnClickListener {
            val card = Card("","")
            CardsApplication.getDeck(deckid)?.addOne(card)

            it.findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToCardEditFragment(card.id, deckid))
        }

        binding.studyDeckFab.setOnClickListener {
            it.findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToStudyFragment(deckid))
        }

        return binding.root
    }
}