package com.hera.users.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.hera.users.R
import com.hera.users.adapters.UserAdapter
import com.hera.users.data.Repository
import com.hera.users.data.models.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val firstPage: Int = 1

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private val scope = CoroutineScope(Dispatchers.Main)
    private val users = MutableLiveData<List<Data>>()

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
                val userResponse = Repository.getUser(firstPage)

                for (page in firstPage..userResponse.total_pages) {
                    users.value = users.value?.plus(Repository.getUser(page).data)
                            ?: Repository.getUser(page).data
            }
        }
    }
}