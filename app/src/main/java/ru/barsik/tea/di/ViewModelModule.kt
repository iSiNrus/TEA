package ru.barsik.tea.di

import ru.barsik.tea.App
import ru.barsik.tea.ui.screens.main.MainScreenViewModel
import toothpick.config.Module

class ViewModelModule(app: App) : Module() {
    init {
        bind(MainScreenViewModel::class.java).toProviderInstance(MainViewModelProvider(app))
    }

}