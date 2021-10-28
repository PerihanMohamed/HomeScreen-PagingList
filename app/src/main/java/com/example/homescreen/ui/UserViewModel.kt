package com.example.homescreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.homescreen.api.ApiService
import com.example.homescreen.data.UserRemoteMediator


import com.example.homescreen.db.UserDatabase
import com.example.homescreen.model.User
import kotlinx.coroutines.flow.Flow

class UserViewModel(val  database: UserDatabase) : ViewModel() {


    fun getUserList( ): Flow<PagingData<User>> {

         val userDatabase = UserDatabase

        @OptIn(ExperimentalPagingApi::class)
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = UserRemoteMediator(
                ApiService.create(),
                database
            ),
            pagingSourceFactory = { database.usersDao().getPosts() }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        const val PAGE_SIZE = 100
    }


}