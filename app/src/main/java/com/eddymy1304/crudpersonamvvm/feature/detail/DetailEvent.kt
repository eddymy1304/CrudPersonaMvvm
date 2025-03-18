package com.eddymy1304.crudpersonamvvm.feature.detail

sealed class DetailEvent {

    data object NavigateToHome : DetailEvent()

}