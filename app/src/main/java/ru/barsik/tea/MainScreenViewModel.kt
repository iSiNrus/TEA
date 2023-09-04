package ru.barsik.tea

import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {

    val store by lazy {
        storeFactory().apply {
            accept(Event.UI.Init)
        }
    }
}