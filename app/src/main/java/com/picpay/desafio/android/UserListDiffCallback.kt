package com.picpay.desafio.android

import androidx.recyclerview.widget.DiffUtil
import com.example.data.entity.UserNetworkEntity

class UserListDiffCallback(
    private val oldList: List<UserNetworkEntity>,
    private val newList: List<UserNetworkEntity>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}