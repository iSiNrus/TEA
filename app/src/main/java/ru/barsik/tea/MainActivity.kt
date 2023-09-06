package ru.barsik.tea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rate_table_feature.ui.RatesScreen
import com.example.rate_table_feature.ui.RatesViewModel
import ru.barsik.tea.ui.screens.main.MainScreenViewModel
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainScreenViewModel
    private lateinit var activityScope: Scope

    @Inject
    lateinit var ratesViewModel: RatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityScope = Toothpick.openScopes(application, this)
        Toothpick.inject(this, activityScope)

        setContent {
//            MainScreen(viewModel)
            RatesScreen(viewModel = ratesViewModel)
        }
    }


}



