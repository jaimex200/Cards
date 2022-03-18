package es.uam.eps.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.cards.databinding.ActivityMainBinding
import timber.log.Timber

private const val TAG : String = "MainActivity"
private const val ANSWERED_KEY = "es.uam.eps.dadm.cards:answered"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    object Model {
        var card: Card = Card("To wake up", "Despertarse")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.card = Model.card
        binding.apply {
            answerButton.setOnClickListener {
                card?.answered = true
                invalidateAll()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }
}