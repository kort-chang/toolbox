package kort.tool.toolbox.view.recyclerview

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Kort on 2019/9/25.
 */
abstract class BaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {
    open var currentList: MutableList<T> = emptyList<T>().toMutableList()
    override fun getItemCount(): Int = currentList.size
    open fun getItem(position: Int) = currentList[position]
    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

abstract class BaseViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}