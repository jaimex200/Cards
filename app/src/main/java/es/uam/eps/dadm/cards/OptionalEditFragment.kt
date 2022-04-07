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
import es.uam.eps.dadm.cards.databinding.FragmentAboutBinding
import es.uam.eps.dadm.cards.databinding.FragmentClozeEditBinding
import es.uam.eps.dadm.cards.databinding.FragmentOptionalEditBinding
import es.uam.eps.dadm.cards.src.Cloze
import es.uam.eps.dadm.cards.src.Options

class OptionalEditFragment : Fragment() {
    lateinit var binding: FragmentOptionalEditBinding
    lateinit var deckid: String
    lateinit var optional: Options
    lateinit var question: String
    lateinit var answerC: String
    lateinit var answerE1: String
    lateinit var answerE2: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentOptionalEditBinding>(
            inflater,
            R.layout.fragment_optional_edit,
            container,
            false
        )

        val args = OptionalEditFragmentArgs.fromBundle(requireArguments())
        optional = CardsApplication.getCard(args.cardid, args.deckid) as Options ?: throw Exception("Wrong id")
        binding.optional = optional

        question = optional.question
        answerC = optional.getAnswer(0)
        answerE1 = optional.getAnswer(1)
        answerE2 = optional.getAnswer(2)
        deckid = args.deckid
        /** ******************************
         * AÑADIR FUNCIONALIDAD A LOS BOTONES DE ACEPPT Y CANCEL
         * CAMBIAR LA VIEW DE LAS LISTAS EN FUNCION DEL TIPO DE TARJETA*/
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val questionTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                optional.question = s.toString()
            }
        }

        val answerCTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                answerC = s.toString()
            }
        }

        val answerE1TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                answerE1 = s.toString()
            }
        }

        val answerE2TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                answerE2 = s.toString()
            }
        }

        binding.questionOptionalEditText.addTextChangedListener(questionTextWatcher)
        binding.answerOptionalCEditText.addTextChangedListener(answerCTextWatcher)
        binding.answerOptionalE1EditText.addTextChangedListener(answerE1TextWatcher)
        binding.answerOptionalE2EditText.addTextChangedListener(answerE2TextWatcher)

        binding.acceptOptionalEditButton.setOnClickListener{
            optional.answer = answerC
            optional.answers = ("$answerC,$answerE1,$answerE2").split(",")
            optional.answersOrd = ("$answerC,$answerE1,$answerE2").split(",")

            view?.findNavController()
                ?.navigate(OptionalEditFragmentDirections.actionOptionalEditFragmentToCardListFragment(deckid))
        }


        binding.cancelOptionalEditButton.setOnClickListener{
            optional.question = question

            view?.findNavController()
                ?.navigate(OptionalEditFragmentDirections.actionOptionalEditFragmentToCardListFragment(deckid))
        }

        binding.deleteOptionalEditButton.setOnClickListener{
            optional.question = question
            CardsApplication.getDeck(deckid)!!.cards.remove(optional)
            view?.findNavController()
                ?.navigate(OptionalEditFragmentDirections.actionOptionalEditFragmentToCardListFragment(deckid))
        }
    }
}