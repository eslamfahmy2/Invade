package com.example.home.domain.models

import com.example.home.R
import com.example.home.data.utils.ApplicationContextSingleton


data class AppOperationState(
    val error: UniversalError? = null,
    val status: DataStatus = DataStatus.CREATED
) {
    enum class DataStatus {
        CREATED, SUCCESS, ERROR, LOADING, COMPLETE
    }
}

fun unknownError() =
    AppOperationState(
        UniversalError(
            ApplicationContextSingleton.getString(
                R.string.unknown_error
            )
        )
    )

fun universalError(error: UniversalError) =
    AppOperationState(error, AppOperationState.DataStatus.ERROR)