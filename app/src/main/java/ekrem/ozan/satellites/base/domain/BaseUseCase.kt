package ekrem.ozan.satellites.base.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<Params, Response>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke(parameters: Params): Flow<Response> {
        return execute(parameters)
            .flowOn(dispatcher)
    }

    protected abstract fun execute(parameters: Params): Flow<Response>
}
