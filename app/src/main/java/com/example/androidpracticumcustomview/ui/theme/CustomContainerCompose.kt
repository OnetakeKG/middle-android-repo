package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */
@Composable
fun CustomContainerCompose(
    firstChild: @Composable (() -> Unit)?,
    secondChild: @Composable (() -> Unit)?,
    durationTranslation: Int,
    durationAlpha: Int
) {
    // Блок создания и инициализации переменных
    var isFirstElementVisible by remember { mutableStateOf(false) }
    var isSecondElementVisible by remember { mutableStateOf(false) }
    val localDensity = LocalDensity.current
    var columnHeight by remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { columnHeight = with(localDensity) { it.size.height.toDp() } }
    ) {
        firstChild?.let {
            AnimatedVisibility(
                visible = isFirstElementVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = durationAlpha)) +
                        slideInVertically(
                            initialOffsetY = { fullHeight: Int -> fullHeight / 2 },
                            animationSpec = tween(
                                durationMillis = durationTranslation,
                                easing = LinearEasing
                            )
                        )
            ) {
                Box(
                    modifier = Modifier
                        .height(columnHeight / 2)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    firstChild()
                }
            }
        }

        secondChild?.let {
            AnimatedVisibility(
                visible = isSecondElementVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = durationAlpha)) +
                        slideInVertically(
                            initialOffsetY = { fullHeight: Int -> -fullHeight / 2 },
                            animationSpec = tween(
                                durationMillis = durationTranslation,
                                easing = LinearEasing
                            )
                        )
            ) {
                Box(
                    modifier = Modifier
                        .height(columnHeight / 2)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    secondChild()
                }
            }

            LaunchedEffect(Unit) {
                isFirstElementVisible = true
                isSecondElementVisible = true
            }
        }
    }
}