package com.eddymy1304.crudpersonamvvm.feature.home

import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel

data class HomeState(
    val persons: List<PersonModel> = listOf(),
    val isLoading: Boolean = false,
    val error: Exception? = null
)