package kort.tool.toolbox.coroutine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


/**
 * Created by Kort on 2020/1/4.
 */

fun <T> Flow<T>.throttleFirst(windowDuration: Long = 500): Flow<T> = flow {
    var windowStartTime = System.currentTimeMillis()
    var emitted = false
    collect { value ->
        val currentTime = System.currentTimeMillis()
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