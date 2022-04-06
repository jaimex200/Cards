package es.uam.eps.dadm.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.cards.databinding.FragmentAboutBinding
import es.uam.eps.dadm.cards.databinding.FragmentOptionalEditBinding

class OptionalEditFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentOptionalEditBinding>(
            inflater,
            R.layout.fragment_optional_edit,
            container,
            false
        )
        /** ******************************
         * AÑADIR FUNCIONALIDAD A LOS BOTONES DE ACEPPT Y CANCEL
         * CAMBIAR LA VIEW DE LAS LISTAS EN FUNCION DEL TIPO DE TARJETA*/
        return binding.root
    }
}