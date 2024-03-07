package com.myapp.myapplication

sealed class FilmzyExceptions : Throwable() {
    object MovieNotFound : FilmzyExceptions()
    object GeneralError : FilmzyExceptions()
}
