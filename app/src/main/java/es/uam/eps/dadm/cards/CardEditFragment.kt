package es.uam.eps.dadm.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.cards.databinding.FragmentCardEditBinding
import es.uam.eps.dadm.cards.databinding.FragmentStudyBinding

class CardEditFragment : Fragment() {
    lateinit var binding: FragmentCardEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_card_edit,
            container,
            false
        )

        return binding.root
    }
}