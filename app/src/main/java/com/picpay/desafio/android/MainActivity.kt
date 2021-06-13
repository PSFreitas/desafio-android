package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.UserRepositoryImpl
import com.example.data.network.api.UserClient
import com.picpay.desafio.android.valuableobject.Status
import com.picpay.desafio.android.viewmodel.MainViewModel
import com.picpay.desafio.android.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val mainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(
                UserRepositoryImpl(
                    userApi = UserClient.userService
                )
            )
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        setupObservables()

    }

    private fun setupObservables() {
        mainViewModel.userList.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    CountingIdleResource.increment()
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    if (it.data != null) {
                        adapter.users = it.data
                    }
                    CountingIdleResource.decrement()
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    Toast.makeText(this@MainActivity, it.exception?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getUsers()
    }
}
