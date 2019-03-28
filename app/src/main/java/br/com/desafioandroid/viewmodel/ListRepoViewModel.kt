package br.com.desafioandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import br.com.desafioandroid.model.RepoJoinOwner
import br.com.desafioandroid.model.dataclass.RepoObject
import br.com.desafioandroid.model.Repository

class ListRepoViewModel : ViewModel(){

    private val repository: Repository = Repository()
    private val repoResult: MutableLiveData<RepoObject> = MutableLiveData()

    init {
        createRepoObject()
    }

    private var repos: LiveData<PagedList<RepoJoinOwner>> = Transformations.switchMap(repoResult) { it ->
        it.data
    }

    private var networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it ->
        it.networkErrors
    }

    private var isRequesting: LiveData<Boolean> = Transformations.switchMap(repoResult) { it ->
        it.isRequesting
    }

    fun createRepoObject(){
        repoResult.postValue(repository.createRepoObject())
    }

    fun getRepos() = repos

    fun getNetworkErrors() = networkErrors

    fun getIsRequesting() = isRequesting

}