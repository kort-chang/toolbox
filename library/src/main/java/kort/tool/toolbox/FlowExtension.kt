package kort.tool.toolbox

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


/**
 * Created by Kort on 2020/1/4.
 */

@InternalCoroutinesApi
fun <T> Flow<T>.throttleFist(windowDuration: Long = 500): Flow<T> = flow {
    var windowStartTime = java.lang.System.currentTimeMillis()
    var emitted = false
    collect { value ->
        val currentTime = java.lang.System.currentTimeMillis()
        val delta = currentTime - windowStartTime
        if (delta >= windowDuration) {
            // if delta is 600,
            // delta / windowDuration would be 1 (Because of the delta and windowDuration are int)
            // and windowStartTime would be added 500.
            // value:           1 --- 2 --- 3 --- 4
            // sendSec:         0    200   200   200
            // throttleBound:                 500
            // emit:            ✔️                ✔️
            windowStartTime += delta / windowDuration * windowDuration
            emitted = false
        }
        if (!emitted) {
            emit(value)
            emitted = true
        }
    }
}