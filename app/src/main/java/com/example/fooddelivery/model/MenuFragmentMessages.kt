package com.example.fooddelivery.model

sealed class MenuFragmentMessages {
    data class MultipleMessages(val messages: List<MenuFragmentMessages>) : MenuFragmentMessages()
    data class ScreenData(
        val listCities: List<String>,
        val listBanners: List<Int>,
        val listMenu: List<String>
    ) : MenuFragmentMessages()
}