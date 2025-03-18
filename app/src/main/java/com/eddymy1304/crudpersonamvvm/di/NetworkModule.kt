package com.eddymy1304.crudpersonamvvm.di

import com.eddymy1304.crudpersonamvvm.BuildConfig
import com.eddymy1304.crudpersonamvvm.core.Api
import com.eddymy1304.crudpersonamvvm.core.SearchDniInterceptor
import com.eddymy1304.crudpersonamvvm.core.ApiType
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIME_DEFAULT = 30L
    private const val MEDIA_TYPE = "application/json"

    @Provides
    @Singleton
    @Api(ApiType.SEARCH_DNI)
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true}
        val converterFactory = json.asConverterFactory(MEDIA_TYPE.toMediaType())
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL_SEARCH_DNI)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(SearchDniInterceptor())
            .connectTimeout(TIME_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(TIME_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(TIME_DEFAULT, TimeUnit.SECONDS)
            .build()
    }

}