package com.example.homescreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homescreen.db.UserDatabase

class ViewModelFactory(val database: UserDatabase):  ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(database) as T
        }
        throw IllegalArgumentException("Exception: Unknown ViewModel class")
    }
}