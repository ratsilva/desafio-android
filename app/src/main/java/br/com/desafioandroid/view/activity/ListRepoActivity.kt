package br.com.desafioandroid.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.desafioandroid.R
import br.com.desafioandroid.databinding.ActivityListrepoBinding
import br.com.desafioandroid.view.adapter.ListRepoAdapter
import br.com.desafioandroid.viewmodel.ListRepoViewModel

class ListRepoActivity : AppCompatActivity() {

    private lateinit var viewModel: ListRepoViewModel
    private lateinit var binding: ActivityListrepoBinding
    private val adapter = ListRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get DataBinding from layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listrepo)
        binding.lifecycleOwner = this

        // Get ViewModel for this Viewm
        viewModel = ViewModelProviders.of(this).get(ListRepoViewModel::class.java)

        // Configure Swipe Layout
        configureSwipeLayout()

        // Configure RecyclerView
        configureRecyclerView()

        // Configure Button to scroll to top of recyclerview
        configureTopButton()
    }

    fun configureSwipeLayout() {

        binding.listrepoSwipelayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {

        })

    }

    fun configureRecyclerView(){

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL

        binding.listrepoRecyclerview.layoutManager = layoutManager
        binding.listrepoRecyclerview.adapter = adapter

    }

    fun configureTopButton(){

        binding.listrepoTopbutton.setOnClickListener(View.OnClickListener {
            //binding.listrepoRecyclerview.smoothScrollToPosition(0)
            binding.listrepoRecyclerview.scrollToPosition(0)
        })

    }

}
