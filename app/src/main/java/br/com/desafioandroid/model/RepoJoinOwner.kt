package br.com.desafioandroid.model

data class RepoJoinOwner(

    var id: Long,
    var name: String,
    var fullName: String,
    var description: String?,
    var url: String,
    var stars: Int,
    var forks: Int,
    var language: String?,
    var page: Int,
    var login_owner: String,
    val owner_id: Long,
    val owner_login: String?,
    val owner_avatar_url: String?

)