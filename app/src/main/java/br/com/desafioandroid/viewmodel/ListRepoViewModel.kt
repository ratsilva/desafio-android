package br.com.desafioandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.desafioandroid.model.retrofit.Item
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.LivePagedListBuilder


class ListRepoViewModel : ViewModel(){

    private var itemPagedList: LiveData<PagedList<Item>>? = null
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, Item>>? = null

    init {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            //.setPageSize()
            .build()

        //itemPagedList = LivePagedListBuilder(, config)

    }

}