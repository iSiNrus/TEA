package ru.barsik.tea.ui.screens.main

data class MainScreenState(
    val isLoading: Boolean = false,
    val enterValue: String = "",
    val exitValue: String = "",
    val rate: String? = null,
    val messageError: String? = null
)