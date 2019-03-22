package br.com.desafioandroid.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRepository {

    @GET("repositories")
    fun getRepositories(@Query("q") q: String,
                        @Query("sort") sort: String,
                        @Query("page") page: Int): Call<Repository>

}