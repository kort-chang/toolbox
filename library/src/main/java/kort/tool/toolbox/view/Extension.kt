package kort.tool.toolbox.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import java.lang.Exception

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

@Target(AnnotationTarget.PROPERTY_GETTER)
@Deprecated(private_get_message, level = DeprecationLevel.ERROR)
annotation class PrivateGet

const val private_get_message = "There is the private getter"
fun privateGet(): Nothing = throw Exception(private_get_message)