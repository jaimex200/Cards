package es.uam.eps.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import es.uam.eps.dadm.cards.databinding.FragmentDeckListBinding
import es.uam.eps.dadm.cards.src.Deck
import java.util.concurrent.Executors

class DeckListFragment: Fragment(){
    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var adapter: DeckAdapter

    private val deckListViewModel by lazy {
        ViewModelProvider(this).get(DeckListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentDeckListBinding>(
            inflater,
            R.layout.fragment_deck_list,
            container,
            false
        )
        adapter = DeckAdapter()
        adapter.data = emptyList()
        binding.deckListRecyclerView.adapter = adapter

        binding.newDeckFab.setOnClickListener {
            val deck = Deck("")
            executor.execute {
                deckListViewModel.addDeck(deck)
            }

            it.findNavController().navigate(DeckListFragmentDirections.actionDeckListFragmentToDeckEditFragment(deck.id))
        }

        deckListViewModel.decks.observe(viewLifecycleOwner) {
            adapter.data = it
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }
}