package com.example.roomforeinkey.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomforeinkey.data.local.dao.ContactDao
import com.example.roomforeinkey.data.local.entities.ContactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContactViewModel(private val dao: ContactDao, private val groupId: Int): ViewModel() {
//    val allContacts: Flow<List<ContactEntity>> = dao.getAllContact(groupId)

    fun getAllContacts( groupId:Int):Flow<List<ContactEntity>>{
        return dao.getAllContact(groupId)
    }

    fun upsertContact(contact: ContactEntity){
        viewModelScope.launch {
            dao.upsertContact(contact)
        }
    }

    fun deleteContact(contact: ContactEntity){
        viewModelScope.launch {
            dao.deleteContact(contact)
        }
    }
}