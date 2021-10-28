package com.example.homescreen.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homescreen.R
import com.example.homescreen.api.ApiService
import com.example.homescreen.databinding.ActivityMainBinding
import com.example.homescreen.db.UserDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    val viewModel by viewModels<UserViewModel>()

    private lateinit var viewModel: UserViewModel
    val userAdapter = UserPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory((UserDatabase.getInstance(this)))
        )
            .get(UserViewModel::class.java)


        setUpRecyclerView()

    }

    fun setUpRecyclerView(){


        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerview.addItemDecoration(decoration)


        binding.apply {
            recyclerview.adapter = userAdapter
            recyclerview.layoutManager = LinearLayoutManager(this@UserActivity)
        }


        lifecycleScope.launch {
            viewModel.getUserList().collectLatest { pagingData ->
                userAdapter.submitData(pagingData)
            }
        }

        binding.recyclerview.adapter = userAdapter.withLoadStateFooter(
            footer = ReposLoadStateAdapter {
                userAdapter.retry()
            }
        )

//        lifecycleScope.launch {
//            ReposLoadStateAdapter.loadStateFlow.collect { loadState ->
//                val refreshState = loadState.refresh
//
//                // Only show the list if refresh succeeds.
//                binding.recyclerview.isVisible = refreshState is LoadState.NotLoading
//                binding.progressBar.isVisible = refreshState is LoadState.Loading
//                binding.emptyList.isVisible = refreshState is LoadState.Error
//
//                if (refreshState is LoadState.Error)
//                    when (refreshState.error as Exception) {
//                        is HttpException ->
//                            binding.emptyList.text = getString(R.string.internal_error)
//                        is IOException ->
//                            binding.emptyList.text = getString(R.string.label_no_internet)
//                    }
//
//                val errorState = loadState.append as? LoadState.Error
//                    ?: loadState.prepend as? LoadState.Error
//                errorState?.let {
//
//                    // //
//                }
//            }
//        }



    }
}