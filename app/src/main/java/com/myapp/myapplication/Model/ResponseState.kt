package com.myapp.myapplication.Model

sealed interface ResponseState {

    class Success(val user: User) : ResponseState

    class Failure(val error: String) : ResponseState
}