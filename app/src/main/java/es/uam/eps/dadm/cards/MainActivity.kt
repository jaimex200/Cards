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
    private var card: Card = Card("Tree", "Árbol")
    private var player: Player = Player()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.card = card
        binding.apply {
            answerButton.setOnClickListener {
                card?.answered = true
                invalidateAll()
            }

            Timber.i("onCreate called")
        }
        Timber.i("onCreate called")
        Timber.i("answered = ${savedInstanceState?.getBoolean(ANSWERED_KEY)}")
    }

    override fun onStart() {
        super.onStart()
        player.start()
        Timber.i("onStart called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("onRestoreInstanceState called")

        card.answered = savedInstanceState?.getBoolean(ANSWERED_KEY) ?: false
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
        binding.invalidateAll()
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onStop() {
        super.onStop()
        player.stop()
        Timber.i("onStop called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState called")
        outState.putBoolean(ANSWERED_KEY, card.answered)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }
}