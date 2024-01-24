package com.example.molecluedemo.presenters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PlusMinusUiItem (
    val addOneElement: AddOneElement,
    val minusOneUiItem: MinusOneUiItem
) : MainState.Loaded.MathUiItem

@Composable
fun PlusMinusPresenter(plusMinusValue: DataProvider.MathRow.PlusMinusValue) = presenter {
    val addOneUiItem = AddOnePresenter(plusMinusValue.addOne)
    val minusOneUiItem = MinusOnePresenter(plusMinusValue.minusOne)

    PlusMinusUiItem(
        addOneElement = addOneUiItem,
        minusOneUiItem = minusOneUiItem
    )
}

@Composable
fun PlusMinusUi(item: PlusMinusUiItem) {
    Column(Modifier.wrapContentHeight().fillMaxWidth()){
        Text(
            text = "Start PlusMinusUi",
            fontSize = 16.sp
        )
        AddOneUi(item.addOneElement)
        MinusOneUi(item.minusOneUiItem)
        Text(
            text = "End PlusMinusUi",
            fontSize = 16.sp
        )
    }

}