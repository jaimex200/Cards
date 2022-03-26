package es.uam.eps.dadm.cards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        findViewById<TextView>(R.id.cards_title_text_view).setOnClickListener {
            //startActivity(Intent(this, MainActivity::class.java))
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra("MESSAGE", "Saludos")
            startActivity(intent)
        }


    }
}