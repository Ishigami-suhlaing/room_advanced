package com.example.roomforeinkey.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import androidx.room.Query
import com.example.roomforeinkey.data.local.entities.GroupEntity


@Dao
interface GroupDao {
    @Upsert
    suspend fun upsertGroup(group: GroupEntity)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)

    @Query("SELECT * FROM `groups`")
     fun getAllGroups(): Flow<List<GroupEntity>>

}