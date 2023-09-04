package ru.barsik.tea.di

import ru.barsik.tea.App
import ru.barsik.tea.ui.screens.main.MainScreenViewModel
import javax.inject.Provider

class MainViewModelProvider(app: App) : Provider<MainScreenViewModel> {

    override fun get(): MainScreenViewModel {
        return MainScreenViewModel()
    }

}