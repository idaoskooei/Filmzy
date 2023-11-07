package com.myapp.myapplication.model

sealed interface ResponseState {

    class Success(val user: User) : ResponseState

    class Failure(val error: String) : ResponseState
}