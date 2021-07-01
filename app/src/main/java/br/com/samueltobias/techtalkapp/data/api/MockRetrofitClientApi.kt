package br.com.samueltobias.techtalkapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MockRetrofitClientApi {
    private const val apiToken = "28f7788751304c76abf32f24090389e9" // Obtenha sua própria key em crudcrud.com

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://crudcrud.com/api/$apiToken/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi(): MockApi = getRetrofit().create(MockApi::class.java)
}