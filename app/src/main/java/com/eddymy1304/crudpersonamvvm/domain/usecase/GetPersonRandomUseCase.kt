package com.eddymy1304.crudpersonamvvm.domain.usecase

import com.eddymy1304.crudpersonamvvm.data.repository.PersonRepository
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPersonRandomUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    operator fun invoke(): Flow<PersonModel?> {
        return personRepository
            .getAll()
            .map { list ->
                list.randomOrNull()
            }
    }
}