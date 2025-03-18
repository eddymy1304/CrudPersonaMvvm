package com.eddymy1304.crudpersonamvvm.data.repository

import android.util.Log
import com.eddymy1304.crudpersonamvvm.core.AppDispatcher
import com.eddymy1304.crudpersonamvvm.core.DataError
import com.eddymy1304.crudpersonamvvm.core.Dispatcher
import com.eddymy1304.crudpersonamvvm.data.database.dao.PersonDao
import com.eddymy1304.crudpersonamvvm.data.database.entity.PersonEntity
import com.eddymy1304.crudpersonamvvm.data.remote.PersonApiService
import com.eddymy1304.crudpersonamvvm.domain.mapper.toDomainFromEntity
import com.eddymy1304.crudpersonamvvm.domain.mapper.toDomainFromResponse
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personDao: PersonDao,
    private val api: PersonApiService,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : PersonRepository {
    override suspend fun save(person: PersonEntity): Result<Unit> {
        return withContext(ioDispatcher) {
            val personEntity = personDao.getByNumberDocument(person.numberDocument).first()
            if (personEntity == null) {
                personDao.save(person)
                Result.success(Unit)
            } else {
                Result.failure(DataError.AlreadyExistsError())
            }
        }
    }

    override suspend fun update(person: PersonEntity): Result<Unit> {
        withContext(ioDispatcher) {
            personDao.update(person)
        }
        return Result.success(Unit)
    }

    override suspend fun delete(person: PersonEntity): Result<Unit> {
        withContext(ioDispatcher) {
            personDao.delete(person)
        }
        return Result.success(Unit)
    }

    override fun getAll(): Flow<List<PersonModel>> {
        return personDao
            .getAll()
            .map { it.toDomainFromEntity() }
            .flowOn(ioDispatcher)
    }

    override fun getPersonByNumberDocument(numberDocument: String): Flow<PersonModel?> {
        return personDao
            .getByNumberDocument(numberDocument)
            .map { it?.toDomainFromEntity() }
            .flowOn(ioDispatcher)
    }

    override suspend fun getPersonByDni(numberDocument: String): Result<PersonModel> {
        return try {
            withContext(ioDispatcher) {
                val response = api.getPersonByDni(numberDocument)

                Log.d("PersonRepositoryImpl", "getPersonByDni: $response")

                if (response.isSuccessful && response.body() != null) {
                    val person = response.body()!!
                    Log.d("PersonRepositoryImpl", "getPersonByDni: $person")
                    Result.success(person.toDomainFromResponse())
                } else {
                    Result.failure(Exception(response.code().toString()))
                }
            }
        } catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "getPersonByDni: $e")
            Result.failure(e)
        }
    }
}