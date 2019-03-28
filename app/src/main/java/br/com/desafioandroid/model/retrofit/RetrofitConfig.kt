package br.com.desafioandroid.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val BASE_URL = "https://api.github.com/"
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitRepository(): RetrofitRepository {
        return retrofit.create(RetrofitRepository::class.java)
    }

}