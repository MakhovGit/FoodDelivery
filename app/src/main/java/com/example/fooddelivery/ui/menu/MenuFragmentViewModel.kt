package com.example.fooddelivery.ui.menu

import androidx.lifecycle.ViewModel
import com.example.fooddelivery.R
import com.example.fooddelivery.model.MenuFragmentMessages
import com.example.fooddelivery.model.SingleLiveEvent

class MenuFragmentViewModel : ViewModel() {
    private val _messagesLiveData = SingleLiveEvent<MenuFragmentMessages>()
    val messagesLiveData: SingleLiveEvent<MenuFragmentMessages> by this::_messagesLiveData

    fun requestScreenData() {
        val listCities = listOf("Москва", "Пермь", "Кострома", "Ярославль")
        val listBanners = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
        val listMenu = listOf("Пицца", "Комбо", "Десерты", "Напитки")
        _messagesLiveData.postValue(
            MenuFragmentMessages.ScreenData(
                listCities,
                listBanners,
                listMenu
            )
        )
    }
}