package com.example.homescreen.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(

    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)
