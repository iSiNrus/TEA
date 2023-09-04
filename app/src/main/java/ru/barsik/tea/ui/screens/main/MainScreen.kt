package ru.barsik.tea.ui.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.barsik.tea.ui.theme.TEATheme
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    val states = viewModel.store.states.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val effects = viewModel.store.effects.collectAsState(initial = null)
    renderEffect(effects, scope, snackbarHostState)
    TEATheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                snackbarHost = { SnackbarHost(snackbarHostState) },
                content = { _ ->
                    if (states.value.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Title("Converter currencies")
                            EditText(state = states.value, store = viewModel.store)
                            ResultText(resultText = states.value.exitValue, prefix = "$")
                            RateText(states.value)
                            ResetButton(store = viewModel.store)
                            BoomButton(store = viewModel.store)
                        }
                    }
                })
        }
    }
}

private fun renderEffect(
    effects: State<MainScreenEffect?>,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    when (effects.value) {
        is MainScreenEffect.ShowBoom -> {
            Log.d("SCREEN", "ru.barsik.tea.ui.screens.main.renderEffect: Boom")
            scope.launch {
                snackbarHostState.showSnackbar("Boom")
            }
        }

        else -> Unit
    }
}

@Composable
fun RateText(state: ScreenState) {
    Text(text = "Rate: ${state.rate}")
}

@Composable
fun ResetButton(store: Store<Event, MainScreenEffect, ScreenState>) {
    Button(onClick = {
        store.accept(Event.UI.ResetValuesEvent)
    }) {
        Text(text = "Reset")
    }
}

@Composable
fun BoomButton(store: Store<Event, MainScreenEffect, ScreenState>) {
    Button(onClick = {
        store.accept(Event.UI.BoomButtonClicked)
    }) {
        Text(text = "Boom!")

    }
}

@Composable
fun ResultText(resultText: String, prefix: String) {
    Text(text = "$prefix $resultText")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    state: ScreenState,
    store: Store<Event, MainScreenEffect, ScreenState>
) {
    TextField(
        value = state.enterValue,
        onValueChange = { newText ->
            store.accept(Event.UI.TextChanged(newText))
        },
        supportingText = {
            state.messageError?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    )
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TEATheme {
        MainScreen(MainScreenViewModel())
    }
}