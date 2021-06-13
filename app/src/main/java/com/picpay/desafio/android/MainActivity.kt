package com.picpay.desafio.android

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.UserRepositoryImpl
import com.example.data.network.api.UserClient
import com.example.domain.ResultData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val repository = UserRepositoryImpl(
        userApi = UserClient.userService
    )

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        CountingIdleResource.increment()

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getUsers()

            if (response is ResultData.Success) {
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    adapter.users = response.data
                    CountingIdleResource.decrement()
                }
            } else if (response is ResultData.Error) {
                runOnUiThread {
                    val message = getString(R.string.error)

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
