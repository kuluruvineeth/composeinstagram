package com.kuluruvineeth.composeinstagram.presentation.Main.Profile.components

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kuluruvineeth.composeinstagram.domain.model.TabModel

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    tabModels : List<TabModel>,
    onTabSelected : (selectedIndex:Int) -> Unit
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    TabRow(
        selectedTabIndex = selectedIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        tabModels.forEachIndexed { index, item ->
            Tab(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onTabSelected(index)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color(0xFF777777)
            ) {
                
            }
        }
    }
}