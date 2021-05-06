package com.hera.users.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hera.users.R
import com.hera.users.data.models.Data

class UserAdapter(private val users: MutableLiveData<List<Data>>)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.avatar)
        val firstName: TextView = view.findViewById(R.id.first_name)
        val lastName: TextView = view.findViewById(R.id.last_name)
        val email: TextView = view.findViewById(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = users.value?.get(position)

        Glide.with(holder.view)
                .load(userData?.avatar)
                .into(holder.avatar)

        holder.firstName.text = userData?.first_name
        holder.lastName.text = userData?.last_name
        holder.email.text = userData?.email
    }
}