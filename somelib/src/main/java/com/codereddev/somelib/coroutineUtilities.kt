package com.codereddev.somelib

import android.util.Log
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.newSingleThreadContext

/**
 * Custom [CoroutineExceptionHandler] which
 * is calling [handleException] for caught
 * exceptions.
 */
private val exceptionHandler = CoroutineExceptionHandler { _, t -> handleException(t) }

internal val POOL = newFixedThreadPoolContext(2 * Runtime.getRuntime().availableProcessors(), "bg") + exceptionHandler

internal val DOWNLOAD_THREAD = newSingleThreadContext("Downloader") + exceptionHandler

internal val BATCH_PROCESSING_THREAD = newSingleThreadContext("BatchProcessing") + exceptionHandler

internal val TRACKING_THREAD = newSingleThreadContext("Tracker") + exceptionHandler

internal val PRELOADING_THREAD = newSingleThreadContext("Preloader") + exceptionHandler

internal val NAV_THREAD_CONTEXT = newSingleThreadContext("NavDelegateThread") + exceptionHandler
/**
 * Handles a [Throwable] inside of
 * Kotlin coroutines.
 *
 * https://github.com/Kotlin/kotlinx.coroutines/issues/61#issuecomment-379719632
 */
private fun handleException(t: Throwable) {
    // Dispatch the throwing to the main thread.
    launch(kotlinx.coroutines.experimental.android.UI) {
        Log.e("SomeLib", t.message)
        t.printStackTrace()
        throw t
    }
}
