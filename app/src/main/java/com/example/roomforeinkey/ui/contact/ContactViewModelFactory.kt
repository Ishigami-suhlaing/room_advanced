package com.example.roomforeinkey.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roomforeinkey.data.local.dao.ContactDao
import com.example.roomforeinkey.data.local.entities.ContactEntity
import com.example.roomforeinkey.ui.group.GroupViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class ContactViewModelFactory(private val dao: ContactDao, private val groupId:Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(dao, groupId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}