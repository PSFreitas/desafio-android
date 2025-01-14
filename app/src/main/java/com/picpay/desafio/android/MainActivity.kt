package com.picpay.desafio.android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.UserRepositoryImpl
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.valuableobject.Status
import com.picpay.desafio.android.viewmodel.MainViewModel
import com.picpay.desafio.android.viewmodel.MainViewModelFactory
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val adapter: UserListAdapter = UserListAdapter()

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).let {
            it.viewModel = mainViewModel
            it.lifecycleOwner = this

            it.recyclerViewUsers.adapter = adapter
            it.recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        }

        setupObservables()

    }

    private fun setupObservables() {
        mainViewModel.userList.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    CountingIdleResource.increment()
                }
                Status.SUCCESS -> {
                    if (it.data != null) {
                        adapter.users = it.data
                    }
                    CountingIdleResource.decrement()
                }
                Status.ERROR -> {
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
