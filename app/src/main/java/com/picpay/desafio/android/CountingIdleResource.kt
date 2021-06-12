package com.picpay.desafio.android

import androidx.test.espresso.idling.CountingIdlingResource

object CountingIdleResource {

    private const val RESOURCE = "SERVER_CALLS"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }

}