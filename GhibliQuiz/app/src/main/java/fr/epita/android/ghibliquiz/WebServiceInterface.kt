package fr.epita.android.ghibliquiz

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceInterface
{
    @GET("films/")
    fun listAllFilms(): Call<List<Film>>

    @GET("films/filmID")
    fun GameForID(@Query("filmID")gameId: Int): Call<List<Film>>

}