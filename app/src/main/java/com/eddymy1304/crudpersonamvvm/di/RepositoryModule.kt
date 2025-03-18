package com.eddymy1304.crudpersonamvvm.di

import com.eddymy1304.crudpersonamvvm.data.repository.PersonRepository
import com.eddymy1304.crudpersonamvvm.data.repository.PersonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPersonRepository(personRepositoryImpl: PersonRepositoryImpl): PersonRepository
}