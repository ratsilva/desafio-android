package br.com.desafioandroid.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.desafioandroid.R
import kotlinx.android.synthetic.main.listpullrequest_item.view.*
import android.net.Uri
import br.com.desafioandroid.app.tool.DateParse
import br.com.desafioandroid.model.PullRequestJoinOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class ListPullRequestAdapter : PagedListAdapter<PullRequestJoinOwner, ListPullRequestAdapter.PullRequestViewHolder>(PullRequestDiffUtilCallback()){

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listpullrequest_item, parent, false)
        return PullRequestViewHolder(view)

    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {

        val item = getItem(position)

        item?.let {

            holder.itemView.listpullrequest_item_title.text = item.pullrequest_title
            holder.itemView.listpullrequest_item_body.text = item.pullrequest_body
            holder.itemView.listpullrequest_item_data.text = DateParse.parseDateView(item.pullrequest_created_at)
            holder.itemView.listpullrequest_item_user_name.text = item.owner_login

            Glide
                .with(holder.itemView.context)
                .load(item.owner_avatar_url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .dontAnimate()
                .placeholder(R.drawable.progress_animation)
                .into(holder.itemView.listpullrequest_item_user_img);

            holder.itemView.setOnClickListener(View.OnClickListener {

                val uri = Uri.parse(item.pullrequest_html_url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                holder.itemView.context.startActivity(intent)

            })


        }

    }

    class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}

    class PullRequestDiffUtilCallback : DiffUtil.ItemCallback<PullRequestJoinOwner>() {

        override fun areItemsTheSame(oldItem: PullRequestJoinOwner, newItem: PullRequestJoinOwner): Boolean {
            return oldItem.pullrequest_id == newItem.pullrequest_id
        }

        override fun areContentsTheSame(oldItem: PullRequestJoinOwner, newItem: PullRequestJoinOwner): Boolean {
            return oldItem.equals(newItem)
        }
    }
}