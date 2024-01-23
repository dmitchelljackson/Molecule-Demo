package com.example.molecluedemo.presenters

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

sealed interface MainState {
    object Loading : MainState

    data class Loaded(
        val list: List<MathUiItem>
    ) : MainState {
        sealed interface MathUiItem
    }
}

@Composable
fun MainPresenter(dataProvider: DataProvider): MainState {
    var inputList: List<DataProvider.MathRow>? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        delay(5000)
        inputList = dataProvider.getRows()
    }

    if (inputList == null) {
        return MainState.Loading
    }

    return inputList?.let { list ->
        MainState.Loaded(
            list.map {
                when (it) {
                    is DataProvider.MathRow.AddOne -> AddOnePresenter(it)
                    is DataProvider.MathRow.MinusOne -> MinusOnePresenter(it)
                    is DataProvider.MathRow.TimesValue -> MultiplyPresenter(it)
                    is DataProvider.MathRow.TwoValue -> TwoValuePresenter(it)
                }
            }
        )
    } ?: MainState.Loading
}

@Composable
fun MainUi(mainState: MainState) {
    when(mainState) {
        is MainState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
            )
        }

        is MainState.Loaded -> {
            LazyColumn(Modifier) {
                mainState.list.forEach {
                    item {
                        when(it) {
                            is AddOneUiItem -> AddOneUi(it)
                            is MinusOneUiItem -> MinusOneUi(it)
                            is TimesUiItem -> MultiplyUi(it)
                            is TwoValueUiItem -> TwoValueUi(it)
                        }
                    }
                }
            }
        }
    }
}

interface DataProvider {
    suspend fun getRows(): List<MathRow>

    sealed interface MathRow {
        data class AddOne(val value: Int) : MathRow
        data class MinusOne(val value: Int) : MathRow
        data class TimesValue(val value: Int, val factor: Float) : MathRow
        data class TwoValue(val value1: Int, val value2: Int) : MathRow
    }
}