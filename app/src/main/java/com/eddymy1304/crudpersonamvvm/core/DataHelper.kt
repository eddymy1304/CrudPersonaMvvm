package com.eddymy1304.crudpersonamvvm.core

import javax.inject.Qualifier


enum class ApiType {
    SEARCH_DNI
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Api(val apiType: ApiType)

enum class AppDispatcher {
    IO,
    DEFAULT,
    MAIN
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: AppDispatcher)