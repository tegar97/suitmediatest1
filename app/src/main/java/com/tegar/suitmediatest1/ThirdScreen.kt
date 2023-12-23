package com.tegar.suitmediatest1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tegar.suitmediatest1.databinding.ActivityThirdScreenBinding
import androidx.activity.viewModels
import androidx.paging.LoadState
import com.tegar.suitmediatest1.databinding.ActivitySecondScreenBinding

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel : ThirdScreenViewModel by  viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.swiperefresh.setOnRefreshListener {
            setUserData()

        }
        setupViews()
        setUserData()
    }

    private fun setupViews() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager

    }

    private fun setUserData() {
        val adapter = UsersAdapter{ user ->
            val intent = Intent()
            intent.putExtra(FIRST_NAME, user.firstName)
            setResult(REQUEST_CODE, intent)
            finish()

        }

        adapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

            if (isListEmpty) {
                binding.emptyMessage.visibility = View.VISIBLE
                binding.rvUsers.visibility = View.GONE
            } else {
                binding.emptyMessage.visibility = View.GONE
                binding.rvUsers.visibility = View.VISIBLE
            }


            if (binding.swiperefresh.isRefreshing && loadState.refresh is LoadState.NotLoading) {
                binding.swiperefresh.isRefreshing = false
            }
        }
        binding.rvUsers.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.users.observe(this) {
            adapter.submitData(lifecycle, it)
        }



    }

    companion  object {
        const val REQUEST_CODE = 110
        const val FIRST_NAME = "extra_first_name"
    }
}