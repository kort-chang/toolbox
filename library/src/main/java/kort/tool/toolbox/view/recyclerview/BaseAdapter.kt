package kort.tool.toolbox.view.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Kort on 2019/9/25.
 */
abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    open var currentList: MutableList<T> = emptyList<T>().toMutableList()
    override fun getItemCount(): Int = currentList.size
    open fun getItem(position: Int): T = currentList[position]
}