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
    private var card: Card = Card("Tree", "Árbol")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.card = card

        binding.apply {
            answerButton.setOnClickListener {
                card?.answered = true
                invalidateAll()
            }
            easyButton.setOnClickListener {
                card?.quality = 5
                card?.update(LocalDateTime.now())
                Toast.makeText(this@MainActivity, "Easiness = " + card?.easiness, Toast.LENGTH_SHORT).show()
            }
            doubtButton.setOnClickListener {
                card?.quality = 3
                card?.update(LocalDateTime.now())
                Toast.makeText(this@MainActivity, "Easiness = " + card?.easiness, Toast.LENGTH_SHORT).show()
            }
            hardButton.setOnClickListener {
                card?.quality = 0
                card?.update(LocalDateTime.now())
                Toast.makeText(this@MainActivity, "Easiness = " + card?.easiness, Toast.LENGTH_SHORT).show()
            }
        }
    }
}