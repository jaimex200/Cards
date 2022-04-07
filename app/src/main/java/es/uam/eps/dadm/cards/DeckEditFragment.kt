package es.uam.eps.dadm.cards

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import es.uam.eps.dadm.cards.databinding.FragmentDeckEditBinding
import es.uam.eps.dadm.cards.src.Deck
import timber.log.Timber

class DeckEditFragment: Fragment() {
    lateinit var binding: FragmentDeckEditBinding
    lateinit var deck: Deck
    lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentDeckEditBinding>(
            inflater,
            R.layout.fragment_deck_edit,
            container,
            false
        )

        val args = DeckEditFragmentArgs.fromBundle(requireArguments())
        deck = CardsApplication.getDeck(args.deckid) ?: throw Exception("Wrong id")
        binding.deck = deck
        name = deck.name

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val nameTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                deck.name = s.toString()
            }
        }

        binding.nameEditText.addTextChangedListener(nameTextWatcher)

        binding.acceptDeckEditButton.setOnClickListener{
            view?.findNavController()
                ?.navigate(DeckEditFragmentDirections.actionDeckEditFragmentToDeckListFragment())
        }


        binding.cancelDeckEditButton.setOnClickListener{
            deck.name = name

            view?.findNavController()
                ?.navigate(DeckEditFragmentDirections.actionDeckEditFragmentToDeckListFragment())
        }

        binding.deleteDeckEditButton.setOnClickListener{

            CardsApplication.decks.remove(deck)
            view?.findNavController()
                ?.navigate(DeckEditFragmentDirections.actionDeckEditFragmentToDeckListFragment())
        }
    }
}