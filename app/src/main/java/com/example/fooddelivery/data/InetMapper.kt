package com.example.fooddelivery.data

import com.example.fooddelivery.data.dto.DataModelDTO
import com.example.fooddelivery.model.WordData
import com.example.fooddelivery.utils.EMPTY

class InetMapper {

    private fun map(dataModelDTO: DataModelDTO) =
        WordData(
            word = dataModelDTO.text ?: String.EMPTY,
            translation = dataModelDTO.meanings?.first()?.translationDTO?.text ?: String.EMPTY,
            imageUrl = dataModelDTO.meanings?.first()?.imageUrl ?: String.EMPTY
        )

    fun map(dataModels: List<DataModelDTO>) = dataModels.map { model -> map(model) }
}