package com.eddymy1304.crudpersonamvvm.di

import com.eddymy1304.crudpersonamvvm.core.Api
import com.eddymy1304.crudpersonamvvm.core.ApiType
import com.eddymy1304.crudpersonamvvm.data.remote.PersonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun providePersonApiService(@Api(ApiType.SEARCH_DNI) retrofit: Retrofit): PersonApiService {
        return retrofit.create(PersonApiService::class.java)
    }
}