package ru.barsik.tea.ui.screens.main

sealed class MainScreenEffect {

    object  NoEffect: MainScreenEffect()
    object ShowBoom : MainScreenEffect()

}
