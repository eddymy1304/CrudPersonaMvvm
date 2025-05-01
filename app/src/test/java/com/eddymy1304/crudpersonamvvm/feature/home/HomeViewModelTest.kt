package com.eddymy1304.crudpersonamvvm.feature.home

import com.eddymy1304.crudpersonamvvm.data.repository.PersonRepository
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import com.eddymy1304.crudpersonamvvm.domain.model.TypeDocument
import com.eddymy1304.crudpersonamvvm.feature.detail.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var personRepository: PersonRepository

    @Mock
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val testDispatcherRule = TestCoroutineRule()

    @Before
    fun setup() {
        viewModel = HomeViewModel(personRepository)
    }

    @Test
    fun `getUiState initial state`() {
        // Verify that the initial state of uiState is HomeState() before 
        // any operation
        // TODO implement test
    }

    @Test
    fun `getUiState loading state`() {
        // Check if uiState emits a loading state (isLoading = true) when getAllPerson() 
        // is called
        // TODO implement test
    }

    @Test
    fun `getUiState success state`() {
        // Verify that uiState emits a success state with the list of persons 
        // (isLoading = false, persons = list) after getAllPerson() successfully retrieves data
        // TODO implement test
    }

    @Test
    fun `getUiState error state`() {
        // Test if uiState emits an error state (isLoading = false, error = Exception) 
        // when getAllPerson() fails to fetch data
        // TODO implement test
    }

    @Test
    fun `getUiEvent initial emission`() {
        // Check that uiEvent does not emit any event initially.
        // TODO implement test
    }

    @Test
    fun `getUiEvent navigate to detail`() {
        // Verify that uiEvent emits a NavigateToDetail event with the correct 
        // PersonModel when navigateToDetail(person) is called
        // TODO implement test
    }

    @Test
    fun `getUiEvent navigate to detail default`() {
        // Check if uiEvent emits NavigateToDetail with a default PersonModel when 
        // navigateToDetail() is called without parameters
        // TODO implement test
    }

    @Test
    fun `getAllPerson success`() = runTest {

        val mockPersons = listOf(
            PersonModel("72554124", "Eddy David", "Mendoza Yamunaque", 29, TypeDocument.DNI),
            PersonModel("73534088", "Estrellita", "Nuñez", 23, TypeDocument.DNI)
        )

        // Simulate repository success
        whenever(personRepository.getAll()).thenReturn(flowOf(mockPersons))

        // Execute function
        viewModel.getAllPerson()

        advanceUntilIdle()

        // Verify
        val result = viewModel.uiState.first()
        assert(result.persons == mockPersons)
        assert(!result.isLoading)
    }

    @Test
    fun `getAllPerson failure`() {
        // Test if getAllPerson() handles errors correctly, updating uiState.error 
        // and setting isLoading to false
        // TODO implement test
    }

    @Test
    fun `getAllPerson empty list`() {
        // Verify that getAllPerson() handles the case when the repository returns an 
        // empty list without errors.
        // TODO implement test
    }

    @Test
    fun `getAllPerson network error`() {
        // Check if getAllPerson() correctly catches network or connection errors from 
        // the repository
        // TODO implement test
    }

    @Test
    fun `onAction add person`() {
        // Test if onAction with AddPerson action calls navigateToDetail()
        // TODO implement test
    }

    @Test
    fun `onAction delete person`() {
        // Verify that onAction with DeletePerson action calls deletePerson(person) 
        // with the correct person
        // TODO implement test
    }

    @Test
    fun `onAction get all person`() {
        // Check if onAction with GetAllPerson action calls getAllPerson()
        // TODO implement test
    }

    @Test
    fun `onAction view person`() {
        // Test if onAction with ViewPerson action calls navigateToDetail(person) 
        // with the correct person
        // TODO implement test
    }

    @Test
    fun `navigateToDetail with person`() {
        // Check if navigateToDetail(person) correctly emits 
        // HomeEvent.NavigateToDetail with the provided PersonModel
        // TODO implement test
    }

    @Test
    fun `navigateToDetail without person`() {
        // Verify that navigateToDetail() without parameters emits 
        // HomeEvent.NavigateToDetail with the default PersonModel
        // TODO implement test
    }

    @Test
    fun `deletePerson success`() {
        // Test if deletePerson() calls personRepository.delete() and handles a 
        // successful deletion
        // TODO implement test
    }

    @Test
    fun `deletePerson failure`() {
        // Check if deletePerson() calls personRepository.delete() and handles a 
        // failed deletion
        // TODO implement test
    }

    @Test
    fun `deletePerson error`() {
        // Test if deletePerson correctly handles error case by not crashing the 
        // application and potentially emitting the error on UI.
        // TODO implement test
    }

    @Test
    fun `getAllPerson multiple calls`() {
        // Test if multiple calls to getAllPerson sequentially or in parallel correctly
        // update the state without inconsistencies.
        // TODO implement test
    }

}