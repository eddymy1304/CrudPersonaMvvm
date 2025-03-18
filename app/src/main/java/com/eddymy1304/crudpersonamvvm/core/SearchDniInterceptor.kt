package com.eddymy1304.crudpersonamvvm.core

import android.util.Log
import com.eddymy1304.crudpersonamvvm.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class SearchDniInterceptor : Interceptor {

    companion object {
        private const val AUTH = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestInit = chain
            .request()
            .newBuilder()
            .addHeader(AUTH, "Bearer ${BuildConfig.API_KEY_SEARCH_DNI}")

        Log.d("Interceptor", requestInit.toString())

        return chain.proceed(requestInit.build())
    }
}