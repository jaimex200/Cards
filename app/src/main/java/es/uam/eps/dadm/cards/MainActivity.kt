package es.uam.eps.dadm.cards

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber
import es.uam.eps.dadm.cards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var listener = View.OnClickListener { v ->
        val quality = when (v?.id) {
            R.id.easy_button    -> 5
            R.id.doubt_button   -> 3
            else -> 0
        }

        viewModel.update(quality)

        if (viewModel.card == null) {
            Toast.makeText(this, resources.getString(R.string.no_cards_review), Toast.LENGTH_LONG).show()
        }

        binding.invalidateAll()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.nDueCards.observe(this, Observer<Int> {
                t -> binding.infoTextView?.text = t.toString() }
        )

        binding.mainViewModel = viewModel
        binding.answerButton.setOnClickListener {
            viewModel?.card?.answered = true
            binding.invalidateAll()
        }

        // Ajusta el escuchador listener a los botones de dificultad
        binding.doubtButton.setOnClickListener(listener)
        binding.easyButton.setOnClickListener(listener)
        binding.hardButton.setOnClickListener(listener)


    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("onRestoreInstanceState called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }
}