package com.example.roomforeinkey.ui.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomforeinkey.data.local.dao.GroupDao
import com.example.roomforeinkey.data.local.entities.GroupEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GroupViewModel(private val dao: GroupDao) : ViewModel(){
    val allGroups: Flow<List<GroupEntity>> = dao.getAllGroups()

    fun upsertGroup(group: GroupEntity){
        viewModelScope.launch {
            dao.upsertGroup(group)
        }
    }

    fun deleteGroup(group: GroupEntity){
        viewModelScope.launch {
            dao.deleteGroup(group)
        }
    }
}