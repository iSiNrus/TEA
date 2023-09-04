package ru.barsik.tea

sealed class Command {

    data class Calculate(val value: String) : Command()

    object ResetValues : Command()

    object LoadRate : Command()

}