package com.example.molecluedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.molecluedemo.presenters.MainUi
import com.example.molecluedemo.ui.theme.MoleclueDemoTheme
import com.example.molecluedemo.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoleclueDemoTheme {
                val viewModel by viewModels<MainViewModel>()
                val state by viewModel.state.collectAsState()
                MainUi(mainState = state)
            }
        }
    }
}