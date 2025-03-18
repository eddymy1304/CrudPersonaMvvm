package com.eddymy1304.crudpersonamvvm.feature.detail

sealed interface DetailAction {

    data object SavePerson : DetailAction

    data object GetPersonByDni : DetailAction

    data class OnChangeNumberDocument(val numberDocument: String) : DetailAction

    data class OnChangeName(val name: String) : DetailAction

    data class OnChangeLastName(val lastName: String) : DetailAction

    data class OnChangeAge(val age: String) : DetailAction

    data object OnChangeImage : DetailAction

}