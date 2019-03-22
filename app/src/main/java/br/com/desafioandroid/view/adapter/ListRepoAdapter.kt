package br.com.desafioandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.desafioandroid.R
import br.com.desafioandroid.model.retrofit.Item
import kotlinx.android.synthetic.main.listrepo_item.view.*

class ListRepoAdapter : PagedListAdapter<Item, ListRepoAdapter.RepoViewHolder>(RepoDiffUtilCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listrepo_item, parent, false)
        return RepoViewHolder(view)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

        val item = getItem(position)

        item?.let {

            holder.itemView.listrepo_name.text = item.full_name
            holder.itemView.listrepo_description.text = item.description

        }

    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}

    class RepoDiffUtilCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.equals(newItem)
        }
    }
}