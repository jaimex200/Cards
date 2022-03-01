package es.uam.eps.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var answerButton: Button
    private lateinit var answerTextView: TextView
    private lateinit var difficultyButtons: LinearLayout
    private lateinit var separadorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answerButton = findViewById(R.id.answer_button)
        answerTextView = findViewById(R.id.answer_text_view)
        difficultyButtons = findViewById(R.id.difficulty_buttons)
        separadorView = findViewById(R.id.separator_view)

        answerButton.setOnClickListener {
            answerTextView.visibility = View.VISIBLE
            answerButton.visibility = View.INVISIBLE
            difficultyButtons.visibility = View.VISIBLE
            separadorView.visibility = View.VISIBLE
        }
    }
}