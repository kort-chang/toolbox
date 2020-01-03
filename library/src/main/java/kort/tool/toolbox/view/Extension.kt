package kort.tool.toolbox.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import java.lang.Exception
import java.lang.reflect.TypeVariable

/**
 * Created by Kort on 2019/9/18.
 */
fun Context.obtainStyleAndRecycle(
    attrs: AttributeSet,
    declareStyleId: IntArray,
    defStyleRes: Int = 0,
    getStyle: TypedArray.() -> Unit
) {
    obtainStyledAttributes(attrs, declareStyleId, 0, defStyleRes).apply {
        getStyle(this)
        recycle()
    }
}

const val private_get_message = "There is the private getter"
fun privateGet(): Nothing = throw Exception(private_get_message)

fun Context.dpToPx(dp: Int) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)

fun Context.dpToPx(dp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)