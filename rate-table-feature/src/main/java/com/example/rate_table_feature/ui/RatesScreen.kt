package com.example.rate_table_feature.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rate_table_feature.ui.elm.RatesScreenEffect
import com.example.rate_table_feature.ui.elm.RatesScreenState

@Composable
fun RatesScreen(viewModel: RatesViewModel) {

    val state = viewModel.store.states.collectAsState()
    val effects = viewModel.store.effects.collectAsState(initial = null)

    Surface {
        if (state.value.isLoading)
            Loading()
        else
            Content(modifier = Modifier.fillMaxWidth(), state.value)
    }
    handleEffect(effects)
}

private fun handleEffect(effects: State<RatesScreenEffect?>) {
    when (effects.value) {
        is RatesScreenEffect.ShowLoadErrorMessage -> {
            Log.d(
                "RATE_SCREEN",
                "handleEffect: ${(effects.value as RatesScreenEffect.ShowLoadErrorMessage).message}"
            )
        }

        else -> Unit
    }
}

@Composable
fun Content(modifier: Modifier, state: RatesScreenState) {
    val scrollState = rememberScrollState(0)
    Column(
        modifier = modifier.fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Контент")

        with(state.rates) {
            if (this == null) Text(text = "Нет контента")
            this?.let {
                Text(text = "Курс ${state.base}")
                Column(modifier = modifier.verticalScroll(scrollState)) {
                    repeat(size) { num ->
                        Text("${keys.toTypedArray()[num]} - ${values.toTypedArray()[num] ?: "None"}")
                    }
                }
            }
        }

    }

}

@Composable
fun Loading() {
    CircularProgressIndicator()
}