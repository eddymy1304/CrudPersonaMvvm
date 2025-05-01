package com.eddymy1304.crudpersonamvvm.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.eddymy1304.crudpersonamvvm.data.repository.PersonRepository
import com.eddymy1304.crudpersonamvvm.domain.mapper.toEntityFromDomain
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import com.eddymy1304.crudpersonamvvm.domain.model.TypeDocument
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val dispatcherRule = TestCoroutineRule()

    private lateinit var viewModel: DetailViewModel

    private lateinit var repository: PersonRepository

    private val testPerson: PersonModel = PersonModel(
        numberDocument = "72554124",
        name = "Eddy David",
        lastName = "Mendoza Yamunaque",
        age = 29,
        typeDocument = TypeDocument.DNI
    )

    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setup() {
        repository = mock()

        savedStateHandle = SavedStateHandle(
            mapOf("numberDocument" to "")
        )

        viewModel = DetailViewModel(savedStateHandle, repository)
    }

    @Test
    fun `Save Person Success`() = runTest {
        // Simular repository save success
        whenever(repository.save(any())).thenReturn(Result.success(Unit))

        val events = mutableListOf<DetailEvent>()
        val job = launch { viewModel.uiEvent.toList(events) } // collect events

        viewModel.savePerson()

        verify(repository.save(testPerson.toEntityFromDomain()))

        assert(events.contains(DetailEvent.NavigateToHome))

        job.cancel()
    }

    @Test
    fun `Save Person Already Exists Error`() {
        // Test if savePerson() handles DataError.AlreadyExistsError when 
        // attempting to save a person that already exists.
        // TODO implement test
    }

    @Test
    fun `Save Person Network Error`() {
        // Test if savePerson() handles DataError.NetworkError when there is a 
        // network issue during the save operation.
        // TODO implement test
    }

    @Test
    fun `Save Person Unknown Error`() {
        // Test if savePerson() handles DataError.UnknownError when an 
        // unexpected error occurs during the save operation.
        // TODO implement test
    }

    @Test
    fun `Save Person with Empty Name`() {
        // Test if savePerson() correctly saves a person when the name field is empty.
        // TODO implement test
    }

    @Test
    fun `Save Person with Empty Last Name`() {
        // Test if savePerson() correctly saves a person when the last name field 
        // is empty.
        // TODO implement test
    }

    @Test
    fun `Save Person with Zero Age`() {
        // Test if savePerson() correctly saves a person when the age is zero.
        // TODO implement test
    }

    @Test
    fun `Save Person with Maximum Number Document`() {
        // Test if savePerson() correctly saves a person when the number document 
        // has the maximum allowed length (8).
        // TODO implement test
    }

    @Test
    fun `Save Person with Valid person Entity`() {
        // Test if savePerson() correctly saves a person with all required 
        // fields populated with valid data.
        // TODO implement test
    }

    @Test
    fun `Save Person with Coroutine Cancellation`() {
        // Test if savePerson() handles coroutine cancellation correctly, ensuring no 
        // leaks or unexpected behavior.
        // TODO implement test
    }

    @Test
    fun `Save Person Multiple Concurrent Calls`() {
        // Test if savePerson() handles multiple concurrent calls correctly without 
        // race conditions or data corruption.
        // TODO implement test
    }

    @Test
    fun `Save Person Repository Failure`() {
        // Test what happens if the repository.save method throws an exception that 
        // is not a DataError.
        // TODO implement test
    }

}