package kort.tool.toolbox.view.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Kort on 2019/9/25.
 */
abstract class BaseAdapter<T, VH : BaseViewHolder> : RecyclerView.Adapter<VH>() {
    open var focusAt: Int? = null
    open var currentList: MutableList<T> = emptyList<T>().toMutableList()
    override fun getItemCount(): Int = currentList.size
    open fun getItem(position: Int): T = currentList[position]

    protected open fun focusAt(position: Int, holder: BaseViewHolder) {
        if (position == focusAt) {
            holder.focus()
            focusAt = null
        }
    }
}

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    open fun focus() {}
}