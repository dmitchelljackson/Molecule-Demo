package com.example.molecluedemo.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun <T> presenter(block: @Composable PresenterScope.() -> T) : T {
    return PresenterScope().block()
}

class PresenterScope {
    val componentList = mutableListOf<Any?>()

    @Composable
    inline fun <reified T: Any?> rememberedForOutput(value: T) : MutableState<T> {
        val rememberedValue = remember {
            mutableStateOf(value)
        }
        componentList.add(rememberedValue.value)
        return rememberedValue
    }

    @Composable
    fun callback(block: () -> Unit) : MutableState<() -> Unit> {
        val callback = remember {
            mutableStateOf(block)
        }
        componentList.add(callback.value)
        return callback
    }

    @Composable
    fun <T> callback(block: (T) -> Unit) : MutableState<(T) -> Unit> {
        val callback = remember {
            mutableStateOf(block)
        }
        componentList.add(callback.value)
        return callback
    }

    @Composable
    fun <T> onOutputValuesChanged(renderBlock: () -> T) : T {
        return if(componentList.isEmpty()) {
            renderBlock()
        } else {
            val state by remember(componentList) {
                mutableStateOf(renderBlock())
            }
            state
        }

    }
}