package com.example.molecluedemo.presenters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TimesUiItem(
    val value: Int,
    val onClick: () -> Unit
) : MainState.Loaded.MathUiItem

@Composable
fun MultiplyPresenter(timesValue: DataProvider.MathRow.TimesValue): TimesUiItem {
    var value by remember { mutableStateOf(timesValue.value) }
    var factor by remember { mutableStateOf(timesValue.factor) }

    return TimesUiItem(
        value = value,
        onClick = {
            value = (value * factor).toInt()
        }
    )
}

@Composable
fun MultiplyUi(item: TimesUiItem) {
    Box(modifier = Modifier
        .wrapContentHeight()
        .padding(4.dp)
        .fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = item.value.toString(),
            fontSize = 24.sp
        )

        Button(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = item.onClick
        ) {
            Text(text = "Click to multiply")
        }
    }
}

@Preview
@Composable
fun MultiplyUiExample() {
    MultiplyUi(TimesUiItem(10, {}))
}