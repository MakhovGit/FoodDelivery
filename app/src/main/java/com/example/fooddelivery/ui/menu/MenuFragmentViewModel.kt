package com.example.fooddelivery.ui.menu

import androidx.lifecycle.ViewModel
import com.example.fooddelivery.R
import com.example.fooddelivery.data.InetMapper
import com.example.fooddelivery.data.dto.DataModelDTO
import com.example.fooddelivery.model.MenuFragmentMessages
import com.example.fooddelivery.model.MenuScreenData
import com.example.fooddelivery.model.SingleLiveEvent
import com.example.fooddelivery.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuFragmentViewModel(private val repository: Repository) : ViewModel() {
    private val _messagesLiveData = SingleLiveEvent<MenuFragmentMessages>()
    val messagesLiveData: SingleLiveEvent<MenuFragmentMessages> by this::_messagesLiveData

    private val inetMapper = InetMapper()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        showDataErrorScreen()
        error.printStackTrace()
    }
    private var queryJob: Job? = null
    private val mainScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + coroutineExceptionHandler)

    private val callBack = object : Callback<List<DataModelDTO>> {

        override fun onResponse(
            call: Call<List<DataModelDTO>>,
            response: Response<List<DataModelDTO>>
        ) {
            val data: List<DataModelDTO>? = response.body()
            data?.let { dataNotNull ->
                if (dataNotNull.isNotEmpty()) {
                    val menuScreenData = MenuScreenData(
                        listCities = LIST_CITIES,
                        listBanners = LIST_BANNERS,
                        listMenu = LIST_MENU,
                        translations = inetMapper.map(dataNotNull)
                    )
                    val messages = listOf(
                        MenuFragmentMessages.ScreenData(menuScreenData),
                        MenuFragmentMessages.ShowData,
                        MenuFragmentMessages.HideLoading,
                        MenuFragmentMessages.HideError
                    )
                    _messagesLiveData.postValue(MenuFragmentMessages.MultipleMessages(messages))
                } else {
                    showDataErrorScreen()
                }
            } ?: run {
                showDataErrorScreen()
            }
        }

        override fun onFailure(call: Call<List<DataModelDTO>>, t: Throwable) {
            showDataErrorScreen()
        }

    }

    private fun showDataErrorScreen() {
        val messages = listOf(
            MenuFragmentMessages.HideLoading,
            MenuFragmentMessages.HideData,
            MenuFragmentMessages.ShowError
        )
        _messagesLiveData.postValue(
            MenuFragmentMessages.MultipleMessages(messages)
        )
    }

    fun onReloadButtonPressed() {
        requestScreenData()
    }

    fun requestScreenData() {
        queryJob?.cancel()
        queryJob = mainScope.launch {
            val messages = listOf(
                MenuFragmentMessages.ShowLoading,
                MenuFragmentMessages.HideData,
                MenuFragmentMessages.HideError
            )
            messagesLiveData.postValue(MenuFragmentMessages.MultipleMessages(messages))
            repository.getData(SEARCH_WORD, callBack)
        }
    }

    companion object {
        private const val SEARCH_WORD = "pizza"
        private val LIST_CITIES = listOf("Москва", "Пермь", "Кострома", "Ярославль")
        private val LIST_BANNERS =
            listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
        private val LIST_MENU = listOf("Пицца", "Комбо", "Десерты", "Напитки")
    }
}