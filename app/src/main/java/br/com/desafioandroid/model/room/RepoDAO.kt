package br.com.desafioandroid.model.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.desafioandroid.model.RepoJoinOwner

@Dao
interface RepoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repoList: List<Repo>)

    @Query("SELECT Repo.*, Owner.* FROM Repo LEFT JOIN Owner ON Owner.owner_login = Repo.login_owner ORDER BY page ASC ")
    fun getAll(): DataSource.Factory<Int, RepoJoinOwner>

}