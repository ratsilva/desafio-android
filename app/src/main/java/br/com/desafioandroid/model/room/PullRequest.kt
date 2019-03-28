package br.com.desafioandroid.model.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
        tableName = "PullRequest",
        indices =   [ Index(value = ["pullrequest_loginOwner"], unique = false),
                      Index(value = ["pullrequest_nameRepo"], unique = false)
                    ]
        )
data class PullRequest (

    @PrimaryKey @field:SerializedName("id") val pullrequest_id: Long,
    @field:SerializedName("title") val pullrequest_title: String,
    @field:SerializedName("body") val pullrequest_body: String,
    @field:SerializedName("state") val pullrequest_state: String,
    @field:SerializedName("html_url") val pullrequest_html_url: String,
    @field:SerializedName("created_at") val pullrequest_created_at: String,
    var pullrequest_loginOwner: String,
    var pullrequest_nameRepo: String

)