package br.com.desafioandroid.model

import androidx.paging.LivePagedListBuilder
import br.com.bancopanchallenge.model.paging.PullRequestBoundaryCallback
import br.com.bancopanchallenge.model.paging.RepoBoundaryCallback
import br.com.desafioandroid.model.dataclass.PullRequestObject
import br.com.desafioandroid.model.retrofit.RetrofitConfig
import br.com.desafioandroid.model.retrofit.RetrofitRepository
import br.com.desafioandroid.model.dataclass.RepoObject
import br.com.desafioandroid.model.room.RoomRepository
import java.util.concurrent.Executors

class Repository {

    private val retrofit: RetrofitRepository = RetrofitConfig().getRetrofitRepository()
    private val room: RoomRepository = RoomRepository(Executors.newSingleThreadExecutor())

    fun createRepoObject(): RepoObject {

        val dataSourceFactory = room.getAllRepos()

        val boundaryCallback = RepoBoundaryCallback(retrofit, room)

        val networkErrors = boundaryCallback.getNetworkErrors()

        val isRequesting = boundaryCallback.isRequesting()

        val data = LivePagedListBuilder(dataSourceFactory, 30)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return RepoObject(data, networkErrors, isRequesting)
    }

    fun createPullRequestObject(owner: String, repo: String): PullRequestObject {

        val dataSourceFactory = room.getAllPullRequests(owner, repo)

        val boundaryCallback = PullRequestBoundaryCallback(retrofit, room, owner, repo)

        val networkErrors = boundaryCallback.getNetworkErrors()

        val isRequesting = boundaryCallback.isRequesting()

        val data = LivePagedListBuilder(dataSourceFactory, 30)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return PullRequestObject(data, networkErrors, isRequesting)
    }

}