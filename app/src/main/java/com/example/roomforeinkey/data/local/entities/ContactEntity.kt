package com.example.roomforeinkey.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "contacts",
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["groupId"],//The column in Group
            childColumns = ["groupOwnerId"],//The column in Contact
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("groupOwnerId")]
)


data class ContactEntity (
    @PrimaryKey(autoGenerate = true)val contactId:Int = 0,
    val name: String,
    val phoneNumber: String,
    val groupOwnerId:Int//group နဲ့ contact ကို ချိတ်ဆက်ပေး
)