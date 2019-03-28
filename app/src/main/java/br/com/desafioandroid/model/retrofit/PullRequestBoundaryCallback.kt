package br.com.bancopanchallenge.model.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import br.com.desafioandroid.model.PullRequestJoinOwner
import br.com.desafioandroid.model.retrofit.RetrofitRepository
import br.com.desafioandroid.model.retrofit.searchPullRequests
import br.com.desafioandroid.model.room.RoomRepository

class PullRequestBoundaryCallback(
    private val retrofit: RetrofitRepository,
    private val room: RoomRepository,
    private val owner: String,
    private val repo: String) : PagedList.BoundaryCallback<PullRequestJoinOwner>() {

    private var lastPage = 1
    private var finishedPages = false
    private val networkErrors = MutableLiveData<String>()
    private var isRequesting = MutableLiveData<Boolean>()

    init {
        isRequesting.postValue(false)
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        getPullRequestData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PullRequestJoinOwner) {
        super.onItemAtEndLoaded(itemAtEnd)
        getPullRequestData()
    }

    private fun getPullRequestData() {

        if (isRequesting.value!! || finishedPages) return

        isRequesting.postValue(true)
        searchPullRequests(retrofit, owner, repo, lastPage, { pulls ->
            room.insertAllPullRequests(pulls) {

                if(pulls.isEmpty()){
                    finishedPages = true
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
