package com.example.fooddelivery.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.fooddelivery.databinding.FragmentMenuBinding
import com.example.fooddelivery.model.MenuFragmentMessages
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val menuFragmentViewModel: MenuFragmentViewModel by viewModel()
    private val bannersAdapter = MenuFragmentBannersAdapter()
    private val menuAdapter = MenuFragmentMenuAdapter()

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
        menuFragmentViewModel.requestScreenData()
    }

    private fun initAdapters() {
        with(binding) {
            fragmentMenuBanners.adapter = bannersAdapter
            fragmentMenuMenuLine.adapter = menuAdapter
        }
    }

    private fun initViewModel() {
        menuFragmentViewModel.messagesLiveData.observe(viewLifecycleOwner) { message ->
            processMessages(message)
        }
    }

    private fun processMultipleMessages(messages: List<MenuFragmentMessages>) {
        messages.forEach { message ->
            processMessages(message)
        }
    }

    private fun processScreenData(
        listCities: List<String>,
        listBanners: List<Int>,
        listMenu: List<String>
    ) {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listCities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fragmentMenuListCities.adapter = adapter
        bannersAdapter.setData(listBanners)
        menuAdapter.setData(listMenu)
    }

    private fun processMessages(message: MenuFragmentMessages) {
        with(message) {
            when (this) {
                is MenuFragmentMessages.MultipleMessages -> processMultipleMessages(messages)
                is MenuFragmentMessages.ScreenData -> processScreenData(
                    listCities,
                    listBanners,
                    listMenu
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}