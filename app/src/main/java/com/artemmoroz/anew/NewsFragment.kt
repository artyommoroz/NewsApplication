package com.artemmoroz.anew

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemmoroz.anew.core.base.BaseFragment
import com.artemmoroz.anew.databinding.FragmentNewsBinding
import com.artemmoroz.anew.news.presentation.NewsAdapter
import com.artemmoroz.anew.news.presentation.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(
    R.layout.fragment_news,
    FragmentNewsBinding::bind
) {

    private val viewModel: NewsViewModel by viewModel()
    private val newsAdapter = NewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeToViewModel()
    }

    private fun initViews() {
        newsAdapter.onItemClicked = {
            findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(it))
        }
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun subscribeToViewModel() {
        viewModel.newsList.observe(this) {
            newsAdapter.submitList(it)
        }
        viewModel.showErrorMessage.observe(this) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
    }
}