package ru.barsik.tea

sealed class Event {

    sealed class UI : Event() {
        object Init : UI()
        class TextChanged(val newText: String) : UI()
        object ResetValuesEvent : UI()
        object BoomButtonClicked : UI()
    }

    sealed class Internal : Event() {
        class AmountCalculated(val amount: Float) : Internal()
        class RateLoaded(val rate: Float) : Internal()
        class InputError(val message: String) : Internal()
    }

}
