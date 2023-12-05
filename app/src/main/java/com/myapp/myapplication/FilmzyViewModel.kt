package com.myapp.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class FilmzyViewModel(private val dispatcher: CoroutineDispatcher): ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = dispatcher
}