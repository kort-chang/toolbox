package kort.tool.toolbox.databinding

import androidx.databinding.ViewDataBinding

/**
 * Created by Kort on 2019/9/19.
 */
inline fun <T : ViewDataBinding> T.executeAfter(block: T.() -> Unit) {
    block()
    executePendingBindings()
}