package com.mcmouse88.customdiagram.utils

import android.content.Context
import kotlin.math.ceil

object AndroidUtils {

    fun dp(context: Context, dp: Int): Int {
        return ceil(context.resources.displayMetrics.density * dp).toInt()
    }
}