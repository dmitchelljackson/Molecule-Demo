package com.example.molecluedemo.presenters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MinusOneUiItem(
    val value: Int,
    val onClick: () -> Unit
) : MainState.Loaded.MathUiItem

@Composable
fun MinusOnePresenter(minusOne: DataProvider.MathRow.MinusOne) = presenter {
    var value by rememberedForOutput(minusOne.value)

    val onClick by callback {
        value--
    }

    onOutputValuesChanged {
        MinusOneUiItem(
            value = value,
            onClick = onClick
        )
    }
}

@Composable
fun MinusOneUi(item: MinusOneUiItem) {
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
            Text(text = "Click to subtract")
        }
    }
}

@Preview
@Composable
fun MinusOneUiExample() {
    MinusOneUi(MinusOneUiItem(10, {}))
}