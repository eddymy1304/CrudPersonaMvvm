package com.eddymy1304.crudpersonamvvm.di

import com.eddymy1304.crudpersonamvvm.core.AppDispatcher
import com.eddymy1304.crudpersonamvvm.core.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Dispatcher(AppDispatcher.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}