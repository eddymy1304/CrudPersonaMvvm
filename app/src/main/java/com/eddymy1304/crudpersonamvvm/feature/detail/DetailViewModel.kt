package com.eddymy1304.crudpersonamvvm.feature.detail

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.eddymy1304.crudpersonamvvm.core.DataError
import com.eddymy1304.crudpersonamvvm.data.repository.PersonRepository
import com.eddymy1304.crudpersonamvvm.domain.mapper.toEntityFromDomain
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.GetPersonByDni
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.OnChangeAge
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.OnChangeImage
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.OnChangeLastName
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.OnChangeName
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.OnChangeNumberDocument
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.SavePerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PersonRepository
) : ViewModel() {

    private val numberDocumentFromHome = savedStateHandle.toRoute<DetailScreen>().numberDocument

    private val _uiState = MutableStateFlow(DetailState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<DetailEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        if (numberDocumentFromHome.isNotBlank()) getPersonByNumberDocument()
    }

    private fun getPersonByNumberDocument() {
        viewModelScope.launch {
            repository.getPersonByNumberDocument(numberDocumentFromHome)
                .onStart { _uiState.value.copy(isLoading = true) }
                .catch { _uiState.value.copy(isLoading = false) }
                .collect { person ->
                    _uiState.update { state ->
                        state.copy(
                            person = person ?: state.person,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun onAction(action: DetailAction) {
        when (action) {
            is GetPersonByDni -> getPersonByDni()
            is OnChangeAge -> onChangeAge(action.age)
            is OnChangeLastName -> onChangeLastName(action.lastName)
            is OnChangeName -> onChangeName(action.name)
            is OnChangeNumberDocument -> onChangeNumberDocument(action.numberDocument)
            is SavePerson -> savePerson()
            is OnChangeImage -> {}
        }
    }

    fun savePerson() {
        viewModelScope.launch {
            repository.save(_uiState.value.person.toEntityFromDomain())
                .onSuccess {
                    _uiEvent.emit(DetailEvent.NavigateToHome)
                }
                .onFailure { e ->
                    when (e as DataError) {
                        is DataError.AlreadyExistsError -> TODO()
                        is DataError.NetworkError -> TODO()
                        is DataError.UnknownError -> TODO()
                    }
                }
        }
    }

    fun onChangeName(name: String) {
        if (name.length > 50) return

        _uiState.update { state ->
            state.copy(
                person = state
                    .person
                    .copy(name = name)
            )
        }
    }

    fun onChangeLastName(lastName: String) {
        if (lastName.length > 50) return

        _uiState.update { state ->
            state.copy(
                person = state
                    .person
                    .copy(lastName = lastName)
            )
        }
    }

    fun onChangeAge(age: String) {
        if (!age.isDigitsOnly() || age.length > 3 || ((age.toIntOrNull() ?: 0) > 140)) return

        _uiState.update { state ->
            state.copy(
                person = state
                    .person
                    .copy(age = if (age.isBlank()) 0 else age.toIntOrNull() ?: 0)
            )
        }
    }

    fun onChangeNumberDocument(number: String) {
        if (!number.isDigitsOnly() || number.length > 8) return

        _uiState.update { state ->
            state.copy(
                person = state
                    .person
                    .copy(numberDocument = number)
            )
        }

    }

    fun getPersonByDni() {
        viewModelScope.launch {
            _uiState.value.copy(isLoading = true)
            repository.getPersonByDni(_uiState.value.person.numberDocument)
                .onSuccess { response ->
                    Log.d("DetailViewModel", "getPersonByDni: $response")
                    _uiState.update { state ->
                        state.copy(
                            person = state.person.copy(
                                name = response.name,
                                lastName = response.lastName
                            ),
                            isLoading = false
                        )
                    }
                }
                .onFailure {
                    _uiState.update { state ->
                        state.copy(isLoading = false)
                    }
                }
        }
    }

}