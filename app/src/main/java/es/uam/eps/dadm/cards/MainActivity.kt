package es.uam.eps.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var answerButton: Button
    private lateinit var answerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answerButton = findViewById(R.id.answer_button)
        answerTextView = findViewById(R.id.answer_text_view)

        answerButton.setOnClickListener {
            answerTextView.visibility = View.VISIBLE
            answerButton.visibility = View.INVISIBLE
        }
    }
}