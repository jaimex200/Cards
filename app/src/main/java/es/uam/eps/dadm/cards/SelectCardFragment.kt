package es.uam.eps.dadm.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import es.uam.eps.dadm.cards.databinding.FragmentAboutBinding
import es.uam.eps.dadm.cards.databinding.FragmentSelectCardBinding
import es.uam.eps.dadm.cards.src.Card
import es.uam.eps.dadm.cards.src.Cloze

class SelectCardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSelectCardBinding>(
            inflater,
            R.layout.fragment_select_card,
            container,
            false
        )

        val args = SelectCardFragmentArgs.fromBundle(requireArguments())

        binding.normalCardButton.setOnClickListener{
            val card = Card("","")
            CardsApplication.getDeck(args.deckid)?.addOne(card)
            it.findNavController()
                .navigate(SelectCardFragmentDirections.actionSelectCardFragmentToCardEditFragment(card.id, args.deckid))
        }

        binding.clozeCardButton.setOnClickListener{
            val cloze = Cloze("","")
            CardsApplication.getDeck(args.deckid)?.addOne(cloze)
            it.findNavController()
                .navigate(SelectCardFragmentDirections.actionSelectCardFragmentToCardEditFragment(cloze.id, args.deckid))
        }

        return binding.root
    }
}