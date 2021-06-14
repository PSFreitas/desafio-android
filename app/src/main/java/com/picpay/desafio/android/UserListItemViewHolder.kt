package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.entity.UserViewEntity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserViewEntity) {
        binding.textViewUsername.text = user.name
        binding.textViewUserNameSurname.text = user.username
        binding.progressBarUserImage.visibility = View.VISIBLE
        Picasso.get()
            .load(user.image)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.circleImageViewUserImage, object : Callback {
                override fun onSuccess() {
                    binding.progressBarUserImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBarUserImage.visibility = View.GONE
                }
            })
    }
}