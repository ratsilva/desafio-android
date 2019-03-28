package br.com.desafioandroid.model.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.desafioandroid.model.PullRequestJoinOwner

@Dao
interface PullRequestDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pullrequestList: List<PullRequest>)

    @Query("SELECT PullRequest.*, Owner.* FROM PullRequest LEFT JOIN Owner ON Owner.owner_login = PullRequest.pullrequest_loginOwner WHERE pullrequest_loginOwner = :owner AND pullrequest_nameRepo = :repo ORDER BY pullrequest_created_at DESC")
    fun getAll(owner: String, repo: String): DataSource.Factory<Int, PullRequestJoinOwner>
}