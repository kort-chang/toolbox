package kort.tool.toolbox

/**
 * Created by Kort on 2019/10/21.
 */
sealed class DataStatus<T> {
    class Loading<T> : DataStatus<T>()
    data class Success<T>(var result: T) : DataStatus<T>()
    data class Failure<T>(var exception: Exception) : DataStatus<T>()

    inline fun <R> flatMap(action: (T) -> DataStatus<R>): DataStatus<R> = when (this) {
        is Loading -> this as Loading<R>
        is Success -> action(result)
        is Failure -> this as Failure<R>
    }

    inline fun <R> map(action: (result: T) -> R): DataStatus<R> = flatMap { Success(action(it)) }
    fun toTaskStatus(): TaskStatus = when (this) {
        is Loading -> TaskStatus.Failure(Exception("loading"))
        is Success -> TaskStatus.Success
        is Failure -> TaskStatus.Failure(exception)
    }


    inline fun <A, R> combine(
        dataStatus: DataStatus<A>,
        action: (T, A) -> R
    ): DataStatus<R> = when (dataStatus) {
        is Success -> {
            map { action(it, dataStatus.result) }
        }
        is Failure -> Failure(dataStatus.exception)
        is Loading -> Loading()
    }
}

sealed class TaskStatus {
    object Success : TaskStatus()
    data class Failure(val exception: Exception) : TaskStatus()
    object Cancel : TaskStatus()
}