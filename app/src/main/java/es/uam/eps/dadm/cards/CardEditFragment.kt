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
import es.uam.eps.dadm.cards.src.Card
import timber.log.Timber

class CardEditFragment : Fragment() {
    lateinit var binding: FragmentCardEditBinding
    lateinit var deckid: String
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
        card = CardsApplication.getCard(args.cardid, args.deckid) ?: throw Exception("Wrong id")
        binding.card = card

        question = card.question
        answer = card.answer
        deckid = args.deckid

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

        binding.questionEditText.addTextChangedListener(questionTextWatcher)
        binding.answerEditText.addTextChangedListener(answerTextWatcher)

        binding.acceptCardEditButton.setOnClickListener{
            view?.findNavController()
                ?.navigate(CardEditFragmentDirections.actionCardEditFragmentToCardListFragment(deckid))
        }


        binding.cancelCardEditButton.setOnClickListener{
            card.answer = answer
            card.question = question

            view?.findNavController()
                ?.navigate(CardEditFragmentDirections.actionCardEditFragmentToCardListFragment(deckid))
        }

        binding.deleteCardEditButton.setOnClickListener{

            CardsApplication.getDeck(deckid)!!.cards.remove(card)
            view?.findNavController()
                ?.navigate(CardEditFragmentDirections.actionCardEditFragmentToCardListFragment(deckid))
        }
    }
}