package ru.barsik.tea

data class ScreenState(
    val isLoading: Boolean = false,
    val enterValue: String = "",
    val exitValue: String = "",
    val rate: String? = null,
    val messageError: String? = null
)