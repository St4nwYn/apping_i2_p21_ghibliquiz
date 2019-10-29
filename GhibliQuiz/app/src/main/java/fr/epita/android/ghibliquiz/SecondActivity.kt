package fr.epita.android.ghibliquiz

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val director = intent.getStringExtra("director")
        val release_date = intent.getStringExtra("release_date")
        //val url = "http://www.google.fr/" + title.replace(' ', "%20")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        detail_title.text = title
        detail_director.text = director
        detail_year.text = release_date
        detail_synopsis.text = description

        more_button.setOnClickListener {
            val url = "http://www.google.fr/search?q=" + title.replace(" ", "+")
            val implicitIntent = Intent(Intent.ACTION_VIEW)
            implicitIntent.data = Uri.parse(url)
            startActivity(implicitIntent)
        }
    }

}
