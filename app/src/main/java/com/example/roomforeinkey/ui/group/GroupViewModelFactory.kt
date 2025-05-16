package com.example.roomforeinkey.ui.group


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomforeinkey.data.local.dao.GroupDao

class GroupViewModelFactory(private val dao: GroupDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroupViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
