package br.com.desafioandroid.model

import androidx.paging.PageKeyedDataSource
import br.com.desafioandroid.model.retrofit.Item

class RepoDataSource : PageKeyedDataSource<Int, Item>(){

    private val FIRST_PAGE = 1
    private val SORT = "language:Java"
    private val Q = "stars"

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
    }

}