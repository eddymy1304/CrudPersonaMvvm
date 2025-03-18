package com.eddymy1304.crudpersonamvvm.data.remote

import com.eddymy1304.crudpersonamvvm.data.remote.response.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApiService {

    @GET("v2/reniec/dni?")
    suspend fun getPersonByDni(
        @Query("numero") numberDocument: String
    ): Response<PersonResponse>

}