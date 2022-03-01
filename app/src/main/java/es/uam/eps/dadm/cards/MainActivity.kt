package es.uam.eps.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import java.time.LocalDateTime
import es.uam.eps.dadm.cards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var card = Card("Tree", "Árbol")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.card = card

        binding.apply {
            answerButton.setOnClickListener {
                answerTextView.visibility = View.VISIBLE
                answerButton.visibility = View.INVISIBLE
                difficultyButtons.visibility = View.VISIBLE
                separatorView.visibility = View.VISIBLE
            }
            easyButton.setOnClickListener {
                card.quality = 5
                card.update(LocalDateTime.now())
                Toast.makeText(this@MainActivity, "Easiness = " + card.easiness, Toast.LENGTH_SHORT).show()
            }
            doubtButton.setOnClickListener {
                card.quality = 3
                card.update(LocalDateTime.now())
                Toast.makeText(this@MainActivity, "Easiness = " + card.easiness, Toast.LENGTH_SHORT).show()
            }
            hardButton.setOnClickListener {
                card.quality = 0
                card.update(LocalDateTime.now())
                Toast.makeText(this@MainActivity, "Easiness = " + card.easiness, Toast.LENGTH_SHORT).show()
            }
        }
    }
}