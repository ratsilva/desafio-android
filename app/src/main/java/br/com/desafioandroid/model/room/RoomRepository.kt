package br.com.desafioandroid.model.room

import androidx.paging.DataSource
import br.com.desafioandroid.app.TembiciApplication
import br.com.desafioandroid.model.PullRequestJoinOwner
import br.com.desafioandroid.model.RepoJoinOwner
import java.util.concurrent.Executor

class RoomRepository(private val ioExecutor: Executor) {

    private val repoDao: RepoDAO = TembiciApplication.database!!.repoDAO()
    private val ownerDao: OwnerDAO = TembiciApplication.database!!.ownerDAO()
    private val pullRequestDAO: PullRequestDAO = TembiciApplication.database!!.pullRequestDAO()

    fun insertAllRepos(repos: List<Repo>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            repoDao.insertAll(repos)
            insertFinished()
        }
    }

    fun getAllRepos(): DataSource.Factory<Int, RepoJoinOwner> {
        return repoDao.getAll()
    }

    fun insertOwner(owner: Owner){
        ioExecutor.execute{
            ownerDao.insert(owner)
        }
    }

    fun insertAllPullRequests(pullrequests: List<PullRequest>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            pullRequestDAO.insertAll(pullrequests)
            insertFinished()
        }
    }

    fun getAllPullRequests(owner: String, repo: String): DataSource.Factory<Int, PullRequestJoinOwner> {
        return pullRequestDAO.getAll(owner, repo)
    }

}