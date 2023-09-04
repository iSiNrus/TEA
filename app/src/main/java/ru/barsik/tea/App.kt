package ru.barsik.tea

import android.app.Application
import ru.barsik.tea.di.ViewModelModule
import toothpick.Scope
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val appScope = Toothpick.openScope(this)
        appScope.installModules(ViewModelModule(this))
    }
}