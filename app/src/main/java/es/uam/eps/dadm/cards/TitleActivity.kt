package es.uam.eps.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.cards.databinding.ActivityTitleBinding

class TitleActivity : AppCompatActivity(), TitleFragment.OnTitleFragmentInteractionListener {
    lateinit var binding: ActivityTitleBinding

    override fun onStudy() {
        val fragment = StudyFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, StudyFragment())
            .addToBackStack("onStudy")
            .commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_title)

        var fragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container)

        if (fragment == null){
            fragment = TitleFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}