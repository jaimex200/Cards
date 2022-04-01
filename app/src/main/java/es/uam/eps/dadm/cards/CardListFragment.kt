package es.uam.eps.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import es.uam.eps.dadm.cards.databinding.FragmentCardListBinding

class CardListFragment: Fragment(){
    lateinit var binding: FragmentCardListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCardListBinding>(
            inflater,
            R.layout.fragment_card_list,
            container,
            false)

        binding.cardListStudyButton.setOnClickListener { view ->
            if (CardsApplication.numberOfDueCards() > 0)
                view.findNavController()
                    .navigate(R.id.action_cardListFragment_to_studyFragment)
            else
                Toast.makeText(
                    requireActivity(),
                    R.string.no_more_cards_toast_message,
                    Toast.LENGTH_LONG
                ).show()
        }

        return binding.root
    }
}