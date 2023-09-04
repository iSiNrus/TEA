package ru.barsik.tea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.barsik.tea.ui.screens.main.MainScreen
import ru.barsik.tea.ui.screens.main.MainScreenViewModel
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainScreenViewModel
    private lateinit var activityScope : Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityScope = Toothpick.openScopes(application, this)
        Toothpick.inject(this, activityScope)

        setContent {
            MainScreen(viewModel)
        }
    }


}



