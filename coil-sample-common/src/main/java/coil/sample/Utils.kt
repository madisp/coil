@file:JvmName("-CommonUtils")

package coil.sample

import android.content.Context
import android.graphics.Color
import android.util.Size
import androidx.annotation.ColorInt
import androidx.lifecycle.AndroidViewModel
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.random.Random

val AndroidViewModel.context: Context
    get() = getApplication()

@ColorInt
fun randomColor(): Int {
    return Color.argb(128, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
}

fun AssetType.next(): AssetType {
    val values = AssetType.values()
    return values[(values.indexOf(this) + 1) % values.size]
}

fun numberOfColumns(context: Context): Int {
    val displayWidth = context.resources.displayMetrics.widthPixels
    val maxColumnWidth = 320 * context.resources.displayMetrics.density
    return ceil(displayWidth / maxColumnWidth).toInt().coerceAtLeast(4)
}

fun Image.calculateScaledSize(context: Context, numColumns: Int): Size {
    val displayWidth = context.resources.displayMetrics.widthPixels
    val columnWidth = (displayWidth / numColumns.toDouble()).roundToInt()
    val scale = columnWidth / width.toDouble()
    return Size(columnWidth, (scale * height).roundToInt())
}
