package es.uam.eps.dadm.cards

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import es.uam.eps.dadm.cards.databinding.FragmentCardEditBinding
import es.uam.eps.dadm.cards.databinding.FragmentClozeEditBinding
import es.uam.eps.dadm.cards.src.Card
import es.uam.eps.dadm.cards.src.Cloze

class ClozeEditFragment : Fragment() {
    lateinit var binding: FragmentClozeEditBinding
    lateinit var deckid: String
    lateinit var cloze: Cloze
    lateinit var question: String
    lateinit var answer: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cloze_edit,
            container,
            false
        )

        val args = CardEditFragmentArgs.fromBundle(requireArguments())
        cloze = CardsApplication.getCard(args.cardid, args.deckid) as Cloze ?: throw Exception("Wrong id")
        binding.cloze = cloze

        question = cloze.question
        answer = cloze.answer
        deckid = args.deckid

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val questionTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cloze.question = s.toString()
            }
        }

        val answerTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cloze.answer = s.toString()
            }
        }

        binding.questionClozeEditText.addTextChangedListener(questionTextWatcher)
        binding.answerClozeEditText.addTextChangedListener(answerTextWatcher)

        binding.acceptClozeEditButton.setOnClickListener{
            view?.findNavController()
                ?.navigate(ClozeEditFragmentDirections.actionClozeEditFragmentToCardListFragment(deckid))
        }


        binding.cancelClozeEditButton.setOnClickListener{
            cloze.answer = answer
            cloze.question = question

            view?.findNavController()
                ?.navigate(ClozeEditFragmentDirections.actionClozeEditFragmentToCardListFragment(deckid))
        }
    }
}