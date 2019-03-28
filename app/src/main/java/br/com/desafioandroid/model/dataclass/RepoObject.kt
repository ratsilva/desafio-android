package br.com.desafioandroid.model.dataclass

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.desafioandroid.model.RepoJoinOwner

data class RepoObject(
    val data: LiveData<PagedList<RepoJoinOwner>>,
    val networkErrors: LiveData<String>,
    val isRequesting: LiveData<Boolean>
)