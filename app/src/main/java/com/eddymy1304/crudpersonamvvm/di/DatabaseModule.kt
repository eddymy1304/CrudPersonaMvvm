package com.eddymy1304.crudpersonamvvm.di

import android.content.Context
import androidx.room.Room
import com.eddymy1304.crudpersonamvvm.data.database.AppDatabase
import com.eddymy1304.crudpersonamvvm.data.database.dao.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "person.db"
            )
            .build()
    }

    @Provides
    fun providePersonDao(appDatabase: AppDatabase): PersonDao = appDatabase.personDao()

}