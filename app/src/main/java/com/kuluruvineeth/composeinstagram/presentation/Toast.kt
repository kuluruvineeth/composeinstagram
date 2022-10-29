package com.kuluruvineeth.composeinstagram.presentation

import android.widget.Toast.makeText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast

@Composable
fun Toast(
    message: String
) {
    makeText(LocalContext.current,message,Toast.LENGTH_SHORT).show()
}