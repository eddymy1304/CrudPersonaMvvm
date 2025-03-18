package com.eddymy1304.crudpersonamvvm.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddymy1304.crudpersonamvvm.data.repository.PersonRepository
import com.eddymy1304.crudpersonamvvm.domain.mapper.toEntityFromDomain
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import com.eddymy1304.crudpersonamvvm.feature.home.HomeAction.AddPerson
import com.eddymy1304.crudpersonamvvm.feature.home.HomeAction.DeletePerson
import com.eddymy1304.crudpersonamvvm.feature.home.HomeAction.GetAllPerson
import com.eddymy1304.crudpersonamvvm.feature.home.HomeAction.ViewPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<HomeEvent>()
    val uiEvent: SharedFlow<HomeEvent> = _uiEvent.asSharedFlow()

    init {
        getAllPerson()
    }

    fun getAllPerson() {
        viewModelScope.launch {
            personRepository
                .getAll()
                .onStart { _uiState.update { state -> state.copy(isLoading = true) } }
                .catch {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = Exception("Error!!!")
                        )
                    }
                }
                .collect { list ->
                    _uiState.update {
                        it.copy(
                            persons = list,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is AddPerson -> navigateToDetail()
            is DeletePerson -> deletePerson(action.person)
            is GetAllPerson -> getAllPerson()
            is ViewPerson -> navigateToDetail(action.person)
        }
    }

    fun navigateToDetail(person: PersonModel = PersonModel()) {
        viewModelScope.launch {
            _uiEvent.emit(HomeEvent.NavigateToDetail(person))
        }
    }

    fun deletePerson(person: PersonModel) {
        viewModelScope.launch {
            personRepository.delete(person.toEntityFromDomain())
                .onSuccess {

                }
                .onFailure {

                }
        }
    }


}