package kort.tool.toolbox

/**
 * Created by Kort on 2019/10/21.
 */
@Suppress("UNCHECKED_CAST")
sealed class Status<T> {
    class Loading<T> : Status<T>()
    data class Success<T>(val result: T) : Status<T>()
    data class Failure<T>(val exception: Exception) : Status<T>()
    class Cancel<T> : Status<T>()

    inline fun <R> flatMap(action: (T) -> Status<R>): Status<R> = when (this) {
        is Loading<*> -> this as Status<R>
        is Success<T> -> action(result)
        is Failure<T> -> this as Failure<R>
        is Cancel<*> -> this as Cancel<R>
    }

    inline fun <R> map(action: (result: T) -> R): Status<R> = flatMap { Success(action(it)) }

    inline fun isSuccess(block: (T) -> Unit) {
        if (this is Success) block(result)
    }

    inline fun <A, R> combine(
        otherStatus: Status<A>,
        action: (T, A) -> R
    ): Status<R> = when (otherStatus) {
        is Success -> {
            map { action(it, otherStatus.result) }
        }
        is Failure -> Failure(otherStatus.exception)
        is Loading -> Loading()
        is Cancel -> Cancel()
    }
}


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

    inline fun isSuccess(block: (T) -> Unit) {
        if (this is Success) {
            block(result)
        }
    }
}

sealed class TaskStatus {
    object Success : TaskStatus()
    data class Failure(val exception: Exception) : TaskStatus()
    object Cancel : TaskStatus()
}