package com.example.homescreen.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homescreen.api.ApiService
import com.example.homescreen.model.User
import retrofit2.HttpException
import java.io.IOException

//
//const val NETWORK_PAGE_SIZE = 100
//private const val STARTING_PAGE_INDEX = 1
//
//
//class UserPagingSource(val apiService: ApiService) : PagingSource<Int, User>() {
//
//
//    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
//        val currentPage = params.key ?: STARTING_PAGE_INDEX
//        return try {
//            val response = apiService.getUsers(currentPage)
//
//            val prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1
//            val nextKey = if (response.isEmpty()) null else currentPage + 1
//            LoadResult.Page(
//                data = response,
//
//                prevKey = prevKey, nextKey = nextKey
//            )
//
//
//        } catch (exception: IOException) {
//            LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            LoadResult.Error(exception)
//        } catch (exception: Exception) {
//            LoadResult.Error(exception)
//        }
//    }
//
//
//}