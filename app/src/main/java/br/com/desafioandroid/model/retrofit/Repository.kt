package br.com.desafioandroid.model.retrofit

import com.google.gson.annotations.SerializedName

class Repository {

    var total_count: Int = 0

    var incomplete_results: Boolean = false

    var items: List<Item>? = null
}

class Item {

    var id: Int = 0

    var name: String = ""

    var full_name: String = ""

    var description: String = ""

    @SerializedName("stargazers_count")
    var stars: Int = 0

    @SerializedName("forks_count")
    var forks: Int = 0

    var owner: Owner? = null

}

class Owner {

    var id: Int = 0
    var login: String = ""
    var avatar_url: String = ""

}