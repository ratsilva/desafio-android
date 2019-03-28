package br.com.bancopanchallenge.model.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import br.com.desafioandroid.model.RepoJoinOwner
import br.com.desafioandroid.model.retrofit.RetrofitRepository
import br.com.desafioandroid.model.retrofit.searchRepos
import br.com.desafioandroid.model.room.RoomRepository

class RepoBoundaryCallback(
    private val retrofit: RetrofitRepository,
    private val room: RoomRepository) : PagedList.BoundaryCallback<RepoJoinOwner>() {

    private var lastPage = 1
    private val networkErrors = MutableLiveData<String>()
    private var isRequesting = MutableLiveData<Boolean>()

    private val Q = "language:Java"
    private val SORT = "stars"

    init {
        isRequesting.postValue(false)
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        getRepoData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: RepoJoinOwner) {
        super.onItemAtEndLoaded(itemAtEnd)
        getRepoData()
    }

    private fun getRepoData() {

        if (isRequesting.value!!) return

        isRequesting.postValue(true)
        searchRepos(retrofit, Q, SORT, lastPage, { repos ->
            room.insertAllRepos(repos) {

                for(repo in repos){
                    room.insertOwner(repo.repo_owner!!)
                }

                lastPage++
                isRequesting.postValue(false)

            }
        }, { error ->
            networkErrors.postValue(error)
            isRequesting.postValue(false)
        })
    }

    fun getNetworkErrors() = networkErrors

    fun isRequesting() = isRequesting
}
