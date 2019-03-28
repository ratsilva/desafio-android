package br.com.desafioandroid.model

class PullRequestJoinOwner(

    val pullrequest_id: Long,
    val pullrequest_title: String,
    val pullrequest_body: String,
    val pullrequest_state: String,
    val pullrequest_html_url: String,
    val pullrequest_created_at: String,
    var pullrequest_loginOwner: String,
    var pullrequest_nameRepo: String,
    val owner_id: Long,
    val owner_login: String?,
    val owner_avatar_url: String?
)