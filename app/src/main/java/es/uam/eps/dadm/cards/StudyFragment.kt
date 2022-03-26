package es.uam.eps.dadm.cards

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.cards.databinding.FragmentStudyBinding
import es.uam.eps.dadm.cards.databinding.FragmentTitleBinding

class StudyFragment: Fragment() {
    lateinit var binding:  FragmentStudyBinding
    private val viewModel: StudyViewModel by lazy {
        ViewModelProvider(this).get(StudyViewModel::class.java)
    }

    private var listener = View.OnClickListener { v ->
        val quality = when (v?.id) {
            R.id.easy_button    -> 5
            R.id.doubt_button   -> 3
            else -> 0
        }

        viewModel.update(quality)

        if (viewModel.card == null) {
            //Toast.makeText(this, resources.getString(R.string.no_cards_review), Toast.LENGTH_LONG).show()
        }

        binding.invalidateAll()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentStudyBinding>(
            inflater,
            R.layout.fragment_study,
            container,
            false)

        binding.mainViewModel = viewModel
        binding.answerButton.setOnClickListener {
            viewModel.card?.answered = true
            binding.invalidateAll()
        }

        // Ajusta el escuchador listener a los botones de dificultad
        binding.doubtButton.setOnClickListener(listener)
        binding.easyButton.setOnClickListener(listener)
        binding.hardButton.setOnClickListener(listener)

        return binding.root
    }
}