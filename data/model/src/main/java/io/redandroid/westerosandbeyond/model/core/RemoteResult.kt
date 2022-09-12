package io.redandroid.westerosandbeyond.model.core

sealed class RemoteResult<T> {
    class Success<T>(val data: T) : RemoteResult<T>()
    class Error<T>(val errorMessage: String) : RemoteResult<T>()
}