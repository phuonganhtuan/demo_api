package com.example.exercise_4_api.base

import com.example.exercise_4_api.utils.CoroutineState

data class Result<out T>(val status: CoroutineState, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T) =
            Result(status = CoroutineState.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String) =
            Result(status = CoroutineState.ERROR, data = data, message = message)

        fun <T> loading(data: T?) =
            Result(status = CoroutineState.LOADING, data = data, message = null)
    }
}
