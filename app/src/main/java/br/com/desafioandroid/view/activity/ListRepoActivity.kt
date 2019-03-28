package br.com.desafioandroid.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.desafioandroid.R
import br.com.desafioandroid.view.adapter.ListRepoAdapter
import br.com.desafioandroid.viewmodel.ListRepoViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import br.com.desafioandroid.databinding.ActivityListRepoBinding
import br.com.desafioandroid.model.RepoJoinOwner
import com.google.android.material.snackbar.Snackbar

class ListRepoActivity : AppCompatActivity() {

    private lateinit var viewModel: ListRepoViewModel
    private lateinit var binding: ActivityListRepoBinding
    private val adapter = ListRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get DataBinding from layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_repo)
        binding.lifecycleOwner = this

        // Get ViewModel for this Viewm
        viewModel = ViewModelProviders.of(this).get(ListRepoViewModel::class.java)

        // Configure RecyclerView
        configureRecyclerView()

        // Configure Button to scroll to top of recyclerview
        configureTopButton()

        // Configure Observable fields from ViewModel
        configureObservableFields()
    }

    fun configureRecyclerView(){

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL

        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        adapter.setHasStableIds(false)

        binding.listrepoRecyclerview.addItemDecoration(dividerItemDecoration)
        binding.listrepoRecyclerview.layoutManager = layoutManager
        binding.listrepoRecyclerview.adapter = adapter

        (binding.listrepoRecyclerview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    fun configureTopButton(){

        binding.listrepoTopbutton.setOnClickListener(View.OnClickListener {
            binding.listrepoRecyclerview.smoothScrollToPosition(0)
        })

    }

    fun configureObservableFields(){

        viewModel.getRepos().observe(this, Observer<PagedList<RepoJoinOwner>> { pagedList ->
            adapter.submitList(pagedList)
        })

        viewModel.getNetworkErrors().observe(this, Observer<String> {
            Snackbar.make(binding.listrepoParent, "Error: " + it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.getIsRequesting().observe(this, Observer<Boolean> {
            showProgressBar(it)
        })


    }

    fun showProgressBar(show: Boolean){

        if(show){
            binding.listrepoProgressbar.visibility = View.VISIBLE
        }else{
            binding.listrepoProgressbar.visibility = View.GONE
        }

    }

}
