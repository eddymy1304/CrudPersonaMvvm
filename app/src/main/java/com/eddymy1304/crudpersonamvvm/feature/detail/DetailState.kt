package com.eddymy1304.crudpersonamvvm.feature.detail

import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel

data class DetailState(
    val person: PersonModel = PersonModel(),
    val isLoading: Boolean = false,
    val error: String? = null
)