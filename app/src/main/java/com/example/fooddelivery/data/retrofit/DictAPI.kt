package com.example.fooddelivery.data.retrofit

import com.example.fooddelivery.data.dto.DataModelDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DictAPI {

    @GET(SEARCH_URL_PART)
    fun search(@Query(QUERY) wordToSearch: String): Call<List<DataModelDTO>>

    companion object {
        private const val SEARCH_URL_PART = "words/search"
        private const val QUERY = "search"
    }
}
