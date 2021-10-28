package com.example.homescreen.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homescreen.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("DELETE FROM user")
    suspend fun clearRepos()

    @Query("SELECT * FROM user")
    fun getPosts(): PagingSource<Int, User>
}