package com.example.fooddelivery.repository

import com.example.fooddelivery.data.dto.DataModelDTO
import com.example.fooddelivery.data.retrofit.DictRemoteDataSource
import retrofit2.Callback

class RepositoryImpl(private val dataSource: DictRemoteDataSource) : Repository {
    override fun getData(word: String, callback: Callback<List<DataModelDTO>>) {
        dataSource.getData(word, callback)
    }
}