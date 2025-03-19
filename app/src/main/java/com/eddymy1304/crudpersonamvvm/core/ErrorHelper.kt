package com.eddymy1304.crudpersonamvvm.core

sealed class DataError : Throwable() {
    class NetworkError : DataError()

    class AlreadyExistsError : DataError()
    class UnknownError : DataError()
}