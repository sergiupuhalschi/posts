package com.sergiupuhalschi.posts.presentation.posts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.sergiupuhalschi.posts.R
import com.sergiupuhalschi.posts.databinding.PostsActivityBinding
import com.sergiupuhalschi.posts.presentation.common.ViewModelActivity
import com.sergiupuhalschi.posts.presentation.details.PostDetailsActivity
import com.sergiupuhalschi.posts.utils.observeNonNull
import javax.inject.Inject

class PostsActivity : ViewModelActivity() {

    @Inject
    lateinit var viewModelFactory: PostsViewModelFactory

    private val viewModel: PostsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: PostsActivityBinding
    private val adapter by lazy { PostsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.posts_activity)
        binding.viewModel = this@PostsActivity.viewModel
        binding.lifecycleOwner = this@PostsActivity

        observeData()
        initViews()
    }

    private fun observeData() {
        viewModel.postViewDataList.observeNonNull(this, {
            adapter.updateList(it)
        })
        viewModel.error.observeNonNull(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.openDetails.observeNonNull(this, {
            startActivity(PostDetailsActivity.newIntent(this, it))
        })
    }

    private fun initViews() {
        binding.postsRV.adapter = adapter
    }
}