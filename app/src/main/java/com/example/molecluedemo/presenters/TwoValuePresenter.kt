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

data class TwoValueUiItem(
    val value: Int,
    val buttonText: String,
    val onClick: () -> Unit
) : MainState.Loaded.MathUiItem

@Composable
fun TwoValuePresenter(twoValue: DataProvider.MathRow.TwoValue): TwoValueUiItem {

    val value1 by remember { mutableStateOf(twoValue.value1) }
    val value2 by remember { mutableStateOf(twoValue.value2) }
    var shouldShowFirstValue by remember { mutableStateOf(true) }

    return TwoValueUiItem(
        value = if (shouldShowFirstValue) {
            value1
        } else {
            value2
        },
        buttonText = if (shouldShowFirstValue) {
            "show second value"
        } else {
            "show first value"
        },
        onClick = {
            shouldShowFirstValue = !shouldShowFirstValue
        }
    )
}

@Composable
fun TwoValueUi(item: TwoValueUiItem) {
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
            Text(text = item.buttonText)
        }
    }
}

@Preview
@Composable
fun TwoValueUiExample() {
    TwoValueUi(TwoValueUiItem(10, "show first value") {})
}