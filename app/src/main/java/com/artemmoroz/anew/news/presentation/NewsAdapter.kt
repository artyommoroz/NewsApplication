package com.artemmoroz.anew.news.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemmoroz.anew.core.utils.DateTimeUtils.getRelativeDate
import com.artemmoroz.anew.databinding.ItemNewsBinding
import com.artemmoroz.anew.news.domain.model.News
import com.bumptech.glide.Glide

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(
    NewsComparator()
) {

    var onItemClicked: (news: News) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, onItemClicked)
    }

    class NewsViewHolder(
        private val view: ItemNewsBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(view.root) {

        fun bind(news: News, onItemClicked: (news: News) -> Unit) {
            view.apply {
                layoutRoot.setOnClickListener { onItemClicked.invoke(news) }
                Glide.with(context).load(news.urlToImage).into(ivPhoto)
                tvTitle.text = news.title
                tvDescription.text = news.description
                tvPublicationDate.text = getRelativeDate(news.publishedAt)
            }
        }

        companion object {
            fun create(parent: ViewGroup): NewsViewHolder {
                val view =
                    ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return NewsViewHolder(view, parent.context)
            }
        }
    }

    class NewsComparator : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.url == newItem.url
        }
    }
}