package com.abdallah.filelistdownloader.domain.useCases

interface UseCase<ReturnType> {
    fun invoke(): ReturnType
}