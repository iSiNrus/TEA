package com.example.rate_table_feature.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rate_table_feature.ui.elm.RatesScreenEffect
import com.example.rate_table_feature.ui.elm.RatesScreenEvent
import com.example.rate_table_feature.ui.elm.RatesScreenState
import vivid.money.elmslie.core.store.Store

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatesScreen(viewModel: RatesViewModel) {

    val state = viewModel.store.states.collectAsState()
    val effects = viewModel.store.effects.collectAsState(initial = null)
    val store = viewModel.store

    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TopAppBar", color = Color.White)
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.ArrowBack, "", tint = Color.White)
                        }
                    },
                    backgroundColor = Color.Blue,
                    elevation = 12.dp
                )
            },
            content = {
                if (state.value.isLoading)
                    Loading()
                else
                    Content(modifier = Modifier.fillMaxWidth(), state.value, store)
            }
        )

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Content(
    modifier: Modifier,
    state: RatesScreenState,
    store: Store<RatesScreenEvent, RatesScreenEffect, RatesScreenState>
) {
    val scrollState = rememberScrollState(0)
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            store.accept(RatesScreenEvent.UI.RefreshRates)
        })
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .pullRefresh(
                state = pullRefreshState
            ).padding(top=80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(state.rates) {
            if (this == null) Text(text = "Нет контента")
            this?.let {
                Text(text = "Курс ${state.base}")
                Column(
                    modifier = modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PullRefreshIndicator(refreshing = state.isLoading, state = pullRefreshState)
                    repeat(size) { num ->
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Black
                                )
                        ) {
                            Text(
                                modifier = modifier.weight(1f, true),
                                text = keys.toTypedArray()[num],
                                textAlign = TextAlign.Start
                            )
                            Text(
                                modifier = modifier.weight(1f, true),
                                text = "${values.toTypedArray()[num] ?: "None"}",
                                textAlign = TextAlign.End
                            )
                        }
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