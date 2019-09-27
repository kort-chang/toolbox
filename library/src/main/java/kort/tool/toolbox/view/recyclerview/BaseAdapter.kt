package kort.tool.toolbox.view.recyclerview

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Kort on 2019/9/25.
 */
abstract class BaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {
    var focusAt: Int? = null
    open var currentList: MutableList<T> = emptyList<T>().toMutableList()
    override fun getItemCount(): Int = currentList.size
    open fun getItem(position: Int): T = currentList[position]
    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
        if (position == focusAt) {
            holder.focus()
            focusAt = null
        }
    }
}

abstract class BaseViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
    open fun focus() {}
}