package ru.barsik.tea

import android.app.Application
import com.example.rate_table_feature.di.RepositoryModule
import ru.barsik.tea.di.ViewModelModule
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val appScope = Toothpick.openScope(this)
        appScope.installModules(ViewModelModule(this), RepositoryModule())
    }
}