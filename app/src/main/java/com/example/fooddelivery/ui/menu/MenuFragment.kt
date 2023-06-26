package com.example.fooddelivery.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.fooddelivery.databinding.FragmentMenuBinding
import com.example.fooddelivery.model.MenuFragmentMessages
import com.example.fooddelivery.model.MenuScreenData
import com.example.fooddelivery.utils.hide
import com.example.fooddelivery.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val menuFragmentViewModel: MenuFragmentViewModel by viewModel()
    private val bannersAdapter = MenuFragmentBannersAdapter()
    private val menuAdapter = MenuFragmentMenuAdapter()
    private val foodListAdapter = MenuFragmentFoodAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        initViewModel()
        initButtons()
        menuFragmentViewModel.requestScreenData()
    }

    private fun initAdapters() {
        with(binding) {
            fragmentMenuBanners.adapter = bannersAdapter
            fragmentMenuMenuLine.adapter = menuAdapter
            fragmentMenuFoodList.adapter = foodListAdapter
        }
    }

    private fun initViewModel() {
        menuFragmentViewModel.messagesLiveData.observe(viewLifecycleOwner) { message ->
            processMessages(message)
        }
    }

    private fun initButtons() {
        binding.fragmentMenuReloadButton.setOnClickListener {
            menuFragmentViewModel.onReloadButtonPressed()
        }
    }

    private fun processMultipleMessages(messages: List<MenuFragmentMessages>) {
        messages.forEach { message ->
            processMessages(message)
        }
    }

    private fun showLoading() {
        binding.fragmentMenuLoadingScreen.show()
    }

    private fun hideLoading() {
        binding.fragmentMenuLoadingScreen.hide()
    }

    private fun showError() {
        binding.fragmentMenuErrorScreen.show()
    }

    private fun hideError() {
        binding.fragmentMenuErrorScreen.hide()
    }

    private fun showData() {
        with(binding) {
            fragmentMenuTopContainer.show()
            fragmentMenuMainContainer.show()
        }
    }

    private fun hideData() {
        with(binding) {
            fragmentMenuTopContainer.hide()
            fragmentMenuMainContainer.hide()
        }
    }

    private fun processCities(cities: List<String>) {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fragmentMenuListCities.adapter = adapter
    }

    private fun processScreenData(data: MenuScreenData) {
        processCities(data.listCities)
        bannersAdapter.setData(data.listBanners)
        menuAdapter.setData(data.listMenu)
        foodListAdapter.setData(data.translations)
    }

    private fun processMessages(message: MenuFragmentMessages) {
        with(message) {
            when (this) {
                is MenuFragmentMessages.MultipleMessages -> processMultipleMessages(messages)
                is MenuFragmentMessages.ScreenData -> processScreenData(data)
                is MenuFragmentMessages.ShowLoading -> showLoading()
                is MenuFragmentMessages.HideLoading -> hideLoading()
                is MenuFragmentMessages.ShowError -> showError()
                is MenuFragmentMessages.HideError -> hideError()
                is MenuFragmentMessages.ShowData -> showData()
                is MenuFragmentMessages.HideData -> hideData()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}