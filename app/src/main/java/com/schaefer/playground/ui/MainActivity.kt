package com.schaefer.playground.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.schaefer.playground.ui.components.MapComponent
import com.schaefer.playground.ui.home.HomeState
import com.schaefer.playground.ui.home.HomeViewModel
import com.schaefer.playground.ui.theme.PlaygroundTheme
import kotlinx.collections.immutable.toPersistentList

class MainActivity : ComponentActivity() {
    val viewModel: HomeViewModel by viewModels { HomeViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.searchVenues(1.35, 103.87)


        setContent {
            val state = viewModel.state.collectAsState().value

            PlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (state) {
                        is HomeState.Loading -> Text(text = "Loading")
                        is HomeState.DataLoaded -> MapComponent(state = state.venues.toPersistentList())
                        is HomeState.Error -> TODO()
                    }
                }
            }
        }
    }
}