package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResultData
import com.example.domain.repository.UserRepository
import com.picpay.desafio.android.entity.UserViewEntity
import com.picpay.desafio.android.mapper.UserViewEntityMapper
import com.picpay.desafio.android.valuableobject.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val userMapper = UserViewEntityMapper()

    private val _userList = MutableLiveData<Resource<List<UserViewEntity>>>()
    val userList: LiveData<Resource<List<UserViewEntity>>> = _userList


    fun getUsers() {
        _userList.value = Resource.loading()

        viewModelScope.launch {
            val resultData = userRepository.getUsers()

            if (resultData is ResultData.Success) {
                val userViewEntityList = userMapper.map(resultData.data)
                _userList.value = Resource.success(userViewEntityList)
            } else if (resultData is ResultData.Error) {
                _userList.value = Resource.error(Exception())
            }

        }

    }


}