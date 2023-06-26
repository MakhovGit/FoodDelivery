package com.example.fooddelivery.repository

import com.example.fooddelivery.data.dto.DataModelDTO

interface Repository {
    fun getData(word: String, callback: retrofit2.Callback<List<DataModelDTO>>)
}