package es.uam.eps.dadm.cards

import androidx.lifecycle.ViewModel
import timber.log.Timber

class MainViewModel : ViewModel() {
    var card = Card("To wake up", "Despertarse")

    init {
        Timber.i("MainViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("MainViewModel destroyed")
    }
}