package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import com.example.androidpracticumcustomview.R

/*
Задание:
Реализуйте необходимые компоненты.
*/

@Composable
fun MainScreen(closeActivity: () -> Unit) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clickable { closeActivity.invoke() },
            contentAlignment = Alignment.Center
        ) {
            CustomContainerCompose(
                firstChild = {
                    Text(
                        stringResource(R.string.first_view_text),
                        modifier = Modifier.background(Color.Yellow).fillMaxSize()
                    )
                },
                secondChild = {
                    Text(
                        stringResource(R.string.second_view_text),
                        modifier = Modifier.background(Color.Red).fillMaxSize()
                    )
                },
                durationTranslation = integerResource(R.integer.translation_duration),
                durationAlpha = integerResource(R.integer.alpha_duration)
            )
        }
    }
}