package com.eddymy1304.crudpersonamvvm.core

sealed class DataError : Exception() {
    class NetworkError : DataError()

    class AlreadyExistsError : DataError()
    class UnknownError : DataError()
}