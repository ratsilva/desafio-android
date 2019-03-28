package br.com.desafioandroid.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.desafioandroid.R
import br.com.desafioandroid.model.RepoJoinOwner
import br.com.desafioandroid.view.activity.PullRequestActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.listrepo_item.view.*

class ListRepoAdapter : PagedListAdapter<RepoJoinOwner, ListRepoAdapter.RepoViewHolder>(RepoDiffUtilCallback()){

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listrepo_item, parent, false)
        return RepoViewHolder(view)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

        val item = getItem(position)

        item?.let {

            holder.itemView.listrepo_name.text = item.fullName
            holder.itemView.listrepo_description.text = item.description
            holder.itemView.listrepo_txt_forks.text = ""+item.forks
            holder.itemView.listrepo_txt_stars.text = ""+item.stars

            holder.itemView.listrepo_user_name.text = item.owner_login

            Glide
                .with(holder.itemView.context)
                .load(item.owner_avatar_url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .dontAnimate()
                .placeholder(R.drawable.progress_animation)
                .into(holder.itemView.listrepo_user_image);

            holder.itemView.setOnClickListener(View.OnClickListener {

                val i = Intent(holder.itemView.context, PullRequestActivity::class.java)
                i.putExtra("owner", item.owner_login)
                i.putExtra("repo", item.name)
                holder.itemView.context.startActivity(i)

            })


        }

    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}

    class RepoDiffUtilCallback : DiffUtil.ItemCallback<RepoJoinOwner>() {

        override fun areItemsTheSame(oldItem: RepoJoinOwner, newItem: RepoJoinOwner): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepoJoinOwner, newItem: RepoJoinOwner): Boolean {
            return oldItem.equals(newItem)
        }
    }
}