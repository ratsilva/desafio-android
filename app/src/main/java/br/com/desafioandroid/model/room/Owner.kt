package br.com.desafioandroid.model.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
        tableName = "Owner",
        indices = [Index(value = ["owner_id"], unique = true)]
        )
data class Owner (
    @PrimaryKey @field:SerializedName("id") val owner_id: Long,
    @field:SerializedName("login") val owner_login: String,
    @field:SerializedName("avatar_url") val owner_avatar_url: String
)