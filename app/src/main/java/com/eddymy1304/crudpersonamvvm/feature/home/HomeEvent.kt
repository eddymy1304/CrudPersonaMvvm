package com.eddymy1304.crudpersonamvvm.feature.home

import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel


sealed class HomeEvent {
    data class NavigateToDetail(val person: PersonModel) : HomeEvent()
}