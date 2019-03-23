package br.com.desafioandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.desafioandroid.model.RepoDataSourceFactory
import br.com.desafioandroid.model.retrofit.Item
import androidx.paging.LivePagedListBuilder

class ListRepoViewModel : ViewModel(){

    private var itemPagedList: LiveData<PagedList<Item>>? = null
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, Item>>? = null

    init {

        val repoDataSourceFactory = RepoDataSourceFactory()
        liveDataSource = repoDataSourceFactory.getRepoLiveDataSource()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        itemPagedList = LivePagedListBuilder(repoDataSourceFactory, config).build()

    }

    fun getListItem() = itemPagedList

    fun getDataSource() = liveDataSource

}