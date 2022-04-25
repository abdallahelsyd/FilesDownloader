package com.abdallah.filesdownloader.domain.usecases

interface UseCase<ReturnType> {
    interface NoParam<TYPE> {
        fun invoke(): TYPE
    }

    interface WithParam<TYPE, PARAM> {
        fun invoke(param: PARAM): TYPE
    }
}