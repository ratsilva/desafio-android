package br.com.desafioandroid.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val BASE_URL = "https://api.stackexchange.com/2.2/"
    private var mInstance: RetrofitConfig? = null
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Synchronized
    fun getInstance(): RetrofitConfig {
        if (mInstance == null) {
            mInstance = RetrofitConfig()
        }
        return mInstance as RetrofitConfig
    }

    fun getRetrofitRepository(): RetrofitRepository {
        return retrofit.create(RetrofitRepository::class.java)
    }

}