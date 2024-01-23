package com.example.molecluedemo.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.AndroidUiDispatcher
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import com.example.molecluedemo.presenters.DataProvider
import com.example.molecluedemo.presenters.MainPresenter
import com.example.molecluedemo.presenters.MainState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow


private val dataProvider = object : DataProvider {
    override suspend fun getRows() = listOf(
        DataProvider.MathRow.AddOne(1),
        DataProvider.MathRow.MinusOne(10),
        DataProvider.MathRow.TimesValue(2, 2f),
        DataProvider.MathRow.TwoValue(10, 20),
        DataProvider.MathRow.TwoValue(100, 200),
        DataProvider.MathRow.AddOne(1),
        DataProvider.MathRow.AddOne(2),
        DataProvider.MathRow.MinusOne(20),
        DataProvider.MathRow.TimesValue(3, 2f),
        DataProvider.MathRow.TwoValue(50, 60),
        DataProvider.MathRow.TwoValue(100, 200),
        DataProvider.MathRow.AddOne(1),
        DataProvider.MathRow.AddOne(1),
        DataProvider.MathRow.MinusOne(10),
        DataProvider.MathRow.TimesValue(2, 3f),
        DataProvider.MathRow.TwoValue(80, 20),
        DataProvider.MathRow.TwoValue(300, 200),
        DataProvider.MathRow.AddOne(111),
        DataProvider.MathRow.AddOne(111),
        DataProvider.MathRow.MinusOne(10),
        DataProvider.MathRow.TimesValue(2, 2f),
        DataProvider.MathRow.TwoValue(10, 20),
        DataProvider.MathRow.TwoValue(100, 200),
        DataProvider.MathRow.AddOne(1),
        )
}

class MainViewModel : MoleculeViewModel<MainState>(
    { MainPresenter(dataProvider = dataProvider) }
)

open class MoleculeViewModel<T>(presenter: @Composable () -> T) : ViewModel() {
    private val scope = CoroutineScope(viewModelScope.coroutineContext + AndroidUiDispatcher.Main)

    val state: StateFlow<T> by lazy(LazyThreadSafetyMode.NONE) {
        scope.launchMolecule(clock = RecompositionClock.ContextClock) {
            presenter()
        }
    }
}