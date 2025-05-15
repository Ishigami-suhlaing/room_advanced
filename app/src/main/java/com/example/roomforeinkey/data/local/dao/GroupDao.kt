package com.example.roomforeinkey

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import androidx.room.Query


@Dao
interface GroupDao {
    @Upsert
    suspend fun upsertGroup(group: GroupEntity)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)

    @Query("SELECT * FROM `groups`")
    suspend fun getAllGroup(): Flow<List<GroupEntity>>

}