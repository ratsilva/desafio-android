package br.com.desafioandroid.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Repo::class),(Owner::class),(PullRequest::class)], version = 1, exportSchema = false)
abstract class TembiciDatabase : RoomDatabase() {
    abstract fun repoDAO(): RepoDAO
    abstract fun ownerDAO(): OwnerDAO
    abstract fun pullRequestDAO(): PullRequestDAO
}