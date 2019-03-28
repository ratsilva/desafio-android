package br.com.desafioandroid.model.retrofit

import br.com.desafioandroid.model.room.Repo
import com.google.gson.annotations.SerializedName

data class ResponseGsonRepo(

    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repo> = emptyList())