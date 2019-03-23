package br.com.desafioandroid.model

import androidx.paging.DataSource
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import br.com.desafioandroid.model.retrofit.Item

class RepoDataSourceFactory : DataSource.Factory<Int, Item>() {

    private val repoLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Item>>()

    override fun create(): DataSource<Int, Item> {
        val repoDataSource = RepoDataSource()
        repoLiveDataSource.postValue(repoDataSource)
        return repoDataSource
    }

    fun getRepoLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Item>> {
        return repoLiveDataSource
    }
}