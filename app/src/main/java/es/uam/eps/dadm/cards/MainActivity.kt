package es.uam.eps.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.cards.databinding.ActivityMainBinding
import timber.log.Timber

private const val TAG : String = "MainActivity"
private const val ANSWERED_KEY = "es.uam.eps.dadm.cards:answered"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.card = viewModel.card
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