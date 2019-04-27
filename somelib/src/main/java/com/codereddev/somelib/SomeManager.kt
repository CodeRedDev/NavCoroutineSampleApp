package com.codereddev.somelib

import kotlin.coroutines.experimental.CoroutineContext

class SomeManager() {
    val coroutineContext: CoroutineContext = PRELOADING_THREAD
}