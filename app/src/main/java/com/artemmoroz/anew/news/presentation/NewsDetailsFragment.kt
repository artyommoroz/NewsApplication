package com.artemmoroz.anew.news.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.artemmoroz.anew.R
import com.artemmoroz.anew.core.base.BaseFragment
import com.artemmoroz.anew.core.utils.DateTimeUtils.getRelativeDate
import com.artemmoroz.anew.databinding.FragmentNewsDetailsBinding
import com.bumptech.glide.Glide

class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>(
    R.layout.fragment_news_details,
    FragmentNewsDetailsBinding::bind
) {

    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            toolbar.title = args.news.title
            tvContent.text = args.news.content
            tvPublicationDate.text = getRelativeDate(args.news.publishedAt)
            Glide.with(requireActivity()).load(args.news.urlToImage).into(ivPhoto)
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}