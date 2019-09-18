package kort.tool.toolbox.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created by Kort on 2019/9/18.
 */
fun <T> MutableLiveData<T>.aware() {
    value = value
}