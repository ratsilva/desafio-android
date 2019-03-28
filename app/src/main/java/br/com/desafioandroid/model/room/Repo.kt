package br.com.desafioandroid.model.room

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repo",
        indices =   [
                        Index(value = ["login_owner"], unique = true)
                    ]
        )
data class Repo (

    @PrimaryKey @field:SerializedName("id") var id: Long,
    @field:SerializedName("name") var name: String,
    @field:SerializedName("full_name") var fullName: String,
    @field:SerializedName("description") var description: String?,
    @field:SerializedName("html_url") var url: String,
    @field:SerializedName("stargazers_count") var stars: Int,
    @field:SerializedName("forks_count") var forks: Int,
    @field:SerializedName("language") var language: String?,
    @field:SerializedName("page") var page: Int,
    @field:SerializedName("idOwner") var login_owner: String,

    @Ignore @field:SerializedName("owner") var repo_owner: Owner?
){
    constructor() : this(0, "", "", "", "", 0, 0, "", 0, "", null)
}