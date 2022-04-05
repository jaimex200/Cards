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
import es.uam.eps.dadm.cards.databinding.FragmentStudyBinding

class CardEditFragment : Fragment() {
    lateinit var binding: FragmentCardEditBinding
    lateinit var card: Card
    lateinit var question: String
    lateinit var answer: String

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

        val args = CardEditFragmentArgs.fromBundle(requireArguments())
        card = CardsApplication.getCard(args.cardid) ?: throw Exception("Wrong id")
        binding.card = card

        question = card.question
        answer = card.answer

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val questionTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                card.question = s.toString()
            }
        }

        val answerTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                card.answer = s.toString()
            }
        }

        binding.cardEditQuestionEdit.addTextChangedListener(questionTextWatcher)
        binding.cardEditAnswerEdit.addTextChangedListener(questionTextWatcher)

        binding.cardEditAcceptEdit.setOnClickListener{
            view?.findNavController()
                ?.navigate(R.id.action_cardEditFragment_to_cardListFragment)
        }

        binding.cardEditCancelEdit.setOnClickListener{
            card.answer = answer
            card.question = question

            view?.findNavController()
                ?.navigate(R.id.action_cardEditFragment_to_cardListFragment)
        }
    }
}