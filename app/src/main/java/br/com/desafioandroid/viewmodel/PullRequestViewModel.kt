package br.com.desafioandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import br.com.desafioandroid.model.PullRequestJoinOwner
import br.com.desafioandroid.model.room.PullRequest
import br.com.desafioandroid.model.Repository
import br.com.desafioandroid.model.dataclass.PullRequestObject

class PullRequestViewModel : ViewModel(){

    private var owner: String = ""
    private var repo: String = ""
    private val repository: Repository = Repository()
    private val pullResult: MutableLiveData<PullRequestObject> = MutableLiveData()

    private var pulls: LiveData<PagedList<PullRequestJoinOwner>> = Transformations.switchMap(pullResult) { it ->
        it.data
    }

    private var networkErrors: LiveData<String> = Transformations.switchMap(pullResult) { it ->
        it.networkErrors
    }

    private var isRequesting: LiveData<Boolean> = Transformations.switchMap(pullResult) { it ->
        it.isRequesting
    }

    fun createPullsObject(){
        pullResult.postValue(repository.createPullRequestObject(owner, repo))
    }

    fun getPulls() = pulls

    fun getNetworkErrors() = networkErrors

    fun getIsRequesting() = isRequesting

    fun setOwner(owner_: String){
        this.owner = owner_
    }

    fun setRepo(repo_: String){
        this.repo = repo_
    }

}