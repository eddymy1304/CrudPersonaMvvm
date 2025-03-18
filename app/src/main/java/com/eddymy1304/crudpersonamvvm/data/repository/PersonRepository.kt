package com.eddymy1304.crudpersonamvvm.data.repository

import com.eddymy1304.crudpersonamvvm.data.database.entity.PersonEntity
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    suspend fun save(person: PersonEntity): Result<Unit>

    suspend fun update(person: PersonEntity): Result<Unit>

    suspend fun delete(person: PersonEntity): Result<Unit>

    fun getAll(): Flow<List<PersonModel>>

    fun getPersonByNumberDocument(numberDocument: String): Flow<PersonModel?>

    suspend fun getPersonByDni(numberDocument: String): Result<PersonModel>

}