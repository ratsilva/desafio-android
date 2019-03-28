package br.com.desafioandroid.model.dataclass

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.desafioandroid.model.PullRequestJoinOwner

data class PullRequestObject(
    val data: LiveData<PagedList<PullRequestJoinOwner>>,
    val networkErrors: LiveData<String>,
    val isRequesting: LiveData<Boolean>
)