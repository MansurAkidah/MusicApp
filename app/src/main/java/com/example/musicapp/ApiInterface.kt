package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("X-RapidAPI-Key:82501211f4msh3a099a43fabf783p1e6606jsnecc361a86c3f" ,
             "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com")
    @GET("search")//making a GET request  with endpoint search
    fun getData(@Query("q")query: String): Call<MyData>//returns a Call and accepts a query parameter of name of the artist
}