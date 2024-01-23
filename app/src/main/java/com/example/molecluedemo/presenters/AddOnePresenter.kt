package com.example.molecluedemo.presenters

import android.util.Log
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

data class AddOneUiItem(
    val value: Int,
    val onClick: () -> Unit
) : MainState.Loaded.MathUiItem

@Composable
fun AddOnePresenter(addOne: DataProvider.MathRow.AddOne): AddOneUiItem {
    var value by remember { mutableStateOf(addOne.value) }

    Log.d("Mitchell", "AddOnePresenter")
    return AddOneUiItem(
        value = value,
        onClick = {
            value++
        }
    )
}

@Composable
fun AddOneUi(item: AddOneUiItem) {
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
            Text(text = "Click to add")
        }
    }
}

@Preview
@Composable
fun AddOneUiExample() {
    AddOneUi(AddOneUiItem(1, {}))
}
