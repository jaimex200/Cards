package es.uam.eps.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = resources.getString(R.string.app_name)

        // Añade aquí el código para crear el Toast
        Toast.makeText(this, name, Toast.LENGTH_LONG).show()
    }
}