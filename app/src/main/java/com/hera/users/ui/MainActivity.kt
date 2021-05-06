package com.hera.users.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.hera.users.R
import com.hera.users.adapters.UserAdapter
import com.hera.users.data.Repository
import com.hera.users.data.models.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private val scope = CoroutineScope(Dispatchers.Main)
    private val users = MutableLiveData<UserResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUsers()

        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        recycler.adapter = UserAdapter(users)

        users.observe(this, Observer {
            recycler.adapter?.notifyDataSetChanged()
        })
    }

    private fun getUsers() {
        scope.launch {
            users.value = Repository.getUser()
        }
    }
}