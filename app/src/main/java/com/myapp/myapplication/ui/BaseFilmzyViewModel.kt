package com.myapp.myapplication.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class BaseFilmzyViewModel(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = dispatcher
}