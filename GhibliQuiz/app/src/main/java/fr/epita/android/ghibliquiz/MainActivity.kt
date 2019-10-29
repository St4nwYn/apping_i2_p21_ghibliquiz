package fr.epita.android.ghibliquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import fr.epita.android.hellogames.FilmAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this@MainActivity)
            .load("https://upload.wikimedia.org/wikipedia/fr/thumb/e/eb/Logo_Ghibli.svg/1200px-Logo_Ghibli.svg.png")
            .into(logo)

        val data = arrayListOf<Film>()
        val baseURL = "https://ghibliapi.herokuapp.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()

        val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)

        val wsCallBack: Callback<List<Film>> = object : Callback<List<Film>> {
            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Log.w("TAG", "WebService call failed")
            }

            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                if (response.code() == 200) {
                    val responseData = response.body()
                    Log.w("CONTENT", "get: " + responseData)
                    if (responseData != null) {
                        Log.d("TAG", "WebService success : " + responseData.size)
                        val film = responseData[(0..responseData.size-1).random()]
                        film_title.text = "'" + film.title + "'?"

                        val onItemClickListener = View.OnClickListener {
                            //val position = it.tag as Int
                            //val clickedItem = responseData[position]

                            val explicitIntent = Intent(this@MainActivity, SecondActivity::class.java)
                            explicitIntent.putExtra("title", film.title)
                            explicitIntent.putExtra("description", film.description)
                            explicitIntent.putExtra("director", film.director)
                            explicitIntent.putExtra("release_date", film.release_date)
                            startActivity(explicitIntent)
                        }

                        recycler.adapter = FilmAdapter(
                            this@MainActivity,
                            responseData.toList(),
                            onItemClickListener
                        )
                    }
                }
            }
        }
        // display performance optimization when list widget size does not change
        recycler.setHasFixedSize(false)
        // here we specify this is a standard vertical list
        recycler.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.VERTICAL, false)

        service.listAllFilms().enqueue(wsCallBack)
    }
}
