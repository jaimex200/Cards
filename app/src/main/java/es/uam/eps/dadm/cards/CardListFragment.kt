package es.uam.eps.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
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
        adapter = CardAdapter(resources)
        deckid = CardListFragmentArgs.fromBundle(requireArguments()).deckid
        adapter.data = CardsApplication.getDeck(deckid)?.cards ?: throw Exception("Wrong id")
        adapter.dataDeck = deckid
        binding.cardListRecyclerView.adapter = adapter

        binding.newCardFab.setOnClickListener {

            it.findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToSelectCardFragment(deckid))
        }

        binding.studyDeckFab.setOnClickListener {

            if (CardsApplication.getDeck(deckid)!!.cards.size > 0)
                it.findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToStudyFragment(deckid))
            else
                Snackbar.make(it, resources.getString(R.string.no_cards_review), Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }
}