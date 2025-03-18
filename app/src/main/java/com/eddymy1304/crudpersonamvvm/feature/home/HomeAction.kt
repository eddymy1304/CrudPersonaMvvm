package com.eddymy1304.crudpersonamvvm.feature.home

import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel

sealed interface HomeAction {

    data object GetAllPerson : HomeAction

    data class DeletePerson(val person: PersonModel) : HomeAction

    data object AddPerson : HomeAction

    data class ViewPerson(val person: PersonModel) : HomeAction

}