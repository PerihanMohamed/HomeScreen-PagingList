package com.example.homescreen.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val userId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
