package br.com.desafioandroid.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.desafioandroid.R
import br.com.desafioandroid.databinding.ActivityListPullrequestBinding
import br.com.desafioandroid.model.PullRequestJoinOwner
import br.com.desafioandroid.view.adapter.ListPullRequestAdapter
import br.com.desafioandroid.viewmodel.PullRequestViewModel
import com.google.android.material.snackbar.Snackbar

class PullRequestActivity : AppCompatActivity() {

    private lateinit var viewModel: PullRequestViewModel
    private lateinit var binding: ActivityListPullrequestBinding
    private val adapter = ListPullRequestAdapter()

    private var owner: String = ""
    private var repo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get DataBinding from layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_pullrequest)
        binding.lifecycleOwner = this

        // Get ViewModel for this Viewm
        viewModel = ViewModelProviders.of(this).get(PullRequestViewModel::class.java)

        // Get extras and define within ViewModel
        configureExtras()

        // Configure RecyclerView
        configureRecyclerView()

        // Configure Button to scroll to top of recyclerview
        configureTopButton()

        // Configure Observable fields from ViewModel
        configureObservableFields()

        setTitle(repo)
    }

    fun configureExtras(){

        var bundle :Bundle ?=intent.extras

        bundle?.let {
            owner = bundle.getString("owner", "")
            repo = bundle.getString("repo", "")
        }

        viewModel.setOwner(owner)
        viewModel.setRepo(repo)
        viewModel.createPullsObject()
    }

    fun configureRecyclerView(){

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL

        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.listpullrequestRecyclerview.addItemDecoration(dividerItemDecoration)
        binding.listpullrequestRecyclerview.layoutManager = layoutManager
        binding.listpullrequestRecyclerview.adapter = adapter

    }

    fun configureTopButton(){

        binding.listpullrequestTopbutton.setOnClickListener(View.OnClickListener {
            binding.listpullrequestRecyclerview.smoothScrollToPosition(0)
        })

    }

    fun configureObservableFields(){

        viewModel.getPulls().observe(this, Observer<PagedList<PullRequestJoinOwner>> { pagedList ->
            adapter.submitList(pagedList)
        })

        viewModel.getNetworkErrors().observe(this, Observer<String> {
            Snackbar.make(binding.listpullrequestParent, "Error: " + it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.getIsRequesting().observe(this, Observer<Boolean> {
            showProgressBar(it)
        })

    }

    fun showProgressBar(show: Boolean){

        if(show){
            binding.listpullrequestProgressbar.visibility = View.VISIBLE
        }else{
            binding.listpullrequestProgressbar.visibility = View.GONE
        }

    }
}