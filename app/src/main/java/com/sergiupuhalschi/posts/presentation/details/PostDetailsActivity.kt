package com.sergiupuhalschi.posts.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.sergiupuhalschi.posts.R
import com.sergiupuhalschi.posts.databinding.PostDetailsActivityBinding
import com.sergiupuhalschi.posts.domain.models.PostDto
import com.sergiupuhalschi.posts.presentation.common.ViewModelActivity
import com.sergiupuhalschi.posts.utils.observeNonNull
import javax.inject.Inject

private const val IN_POST_KEY = "IN_POST_KEY"

class PostDetailsActivity : ViewModelActivity() {

    @Inject
    lateinit var viewModelFactory: PostDetailsViewModelFactory
    private val viewModel: PostDetailsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: PostDetailsActivityBinding
    private val commentsAdapter by lazy { CommentsAdapter() }

    companion object {

        fun newIntent(
            context: Context,
            post: PostDto
        ) = Intent(context, PostDetailsActivity::class.java).apply {
            putExtra(IN_POST_KEY, post)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.post_details_activity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val post = intent.getParcelableExtra<PostDto>(IN_POST_KEY)
            ?: throw IllegalArgumentException("PostDto argument is required")
        viewModel.setPost(post)

        initViews()
        observeData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        binding.commentsRV.adapter = commentsAdapter
        binding.commentsSRL.setOnRefreshListener { viewModel.loadComments() }
    }

    private fun observeData() {
        viewModel.title.observeNonNull(this, { supportActionBar?.title = it })
        viewModel.comments.observeNonNull(this, { commentsAdapter.setComments(it) })
        viewModel.isLoading.observeNonNull(this, { binding.commentsSRL.isRefreshing = it })
    }
}