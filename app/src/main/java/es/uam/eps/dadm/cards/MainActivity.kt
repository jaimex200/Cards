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
    private lateinit var answerButton: Button
    private lateinit var easyButton: Button
    private lateinit var doubtButton: Button
    private lateinit var difficultButton: Button
    private lateinit var answerTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var difficultyButtons: LinearLayout
    private lateinit var separadorView: View
    lateinit var binding: ActivityMainBinding
    var card = Card("TEST", "PRUEBA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)

        answerButton = findViewById(R.id.answer_button)
        questionTextView = findViewById(R.id.question_text_view)
        answerTextView = findViewById(R.id.answer_text_view)
        difficultyButtons = findViewById(R.id.difficulty_buttons)
        separadorView = findViewById(R.id.separator_view)

        easyButton = findViewById(R.id.easy_button)
        doubtButton = findViewById(R.id.doubt_button)
        difficultButton = findViewById(R.id.difficult_button)

        questionTextView.text = card.question
        answerTextView.text = card.answer

        answerButton.setOnClickListener {
            answerTextView.visibility = View.VISIBLE
            answerButton.visibility = View.INVISIBLE
            difficultyButtons.visibility = View.VISIBLE
            separadorView.visibility = View.VISIBLE
        }

        fun exec () {
            card.update(LocalDateTime.now())
            Toast.makeText(this, "Easiness = ${"%.2f".format(card.easiness)}", Toast.LENGTH_LONG).show()
        }

        easyButton.setOnClickListener {
            card.quality = 0
            exec()
        }

        doubtButton.setOnClickListener {
            card.quality = 3
            exec()
        }

        difficultButton.setOnClickListener {
            card.quality = 5
            exec()
        }
    }
}