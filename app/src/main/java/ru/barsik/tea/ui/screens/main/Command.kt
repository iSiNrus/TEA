package ru.barsik.tea.ui.screens.main

sealed class Command {

    data class Calculate(val value: String) : Command()

    object ResetValues : Command()

    object LoadRate : Command()

}