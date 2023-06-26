package com.example.fooddelivery.model

sealed class MenuFragmentMessages {
    data class MultipleMessages(val messages: List<MenuFragmentMessages>) : MenuFragmentMessages()
    data class ScreenData(val data: MenuScreenData) : MenuFragmentMessages()
    object ShowLoading : MenuFragmentMessages()
    object HideLoading : MenuFragmentMessages()
    object ShowError : MenuFragmentMessages()
    object HideError : MenuFragmentMessages()
    object ShowData : MenuFragmentMessages()
    object HideData : MenuFragmentMessages()
}