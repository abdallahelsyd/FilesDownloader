package com.abdallah.filesdownloader.common.base


import com.abdallah.filesdownloader.di.ContextProviders
import com.abdallah.filesdownloader.di.ResourcesHandler
import com.google.gson.Gson
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


abstract class BaseRepository(private val contextProviders: ContextProviders) {

    @Inject
    lateinit var resourcesHandler: ResourcesHandler


    fun <T> networkHandler(fetch: suspend () -> T) = flow {

        // trying to invoke the passed function
        // and emit it's value
        try {
            emit(fetch.invoke())
        }

        // there has been an exception
        // so we might need to respond to it differently
        catch (throwable: Throwable) {

            when (throwable) {

                // network timeout exception due to OkHttpClient timeout configurations
                is TimeoutCancellationException -> throw AppException(resourcesHandler.NETWORK_ERROR_TIMEOUT)

                // might be due to no wifi enabled or network.
                is IOException -> throw AppException(resourcesHandler.NETWORK_ERROR)


                // some other exception
                else -> throw AppException(throwable.message)
            }

        }

    }.flowOn(contextProviders.IO)


}
